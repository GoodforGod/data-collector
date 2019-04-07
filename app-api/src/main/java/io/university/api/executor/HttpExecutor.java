package io.university.api.executor;

import io.university.api.error.ApiTimeoutException;
import io.university.api.error.ConnectionException;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.InflaterInputStream;

import static java.net.HttpURLConnection.HTTP_MOVED_PERM;
import static java.net.HttpURLConnection.HTTP_MOVED_TEMP;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 07.04.2019
 */
public class HttpExecutor implements IHttpExecutor {

    private static final Map<String, String> DEFAULT_HEADERS = new HashMap<>();

    private static final int CONNECT_TIMEOUT = 8000;
    private static final int READ_TIMEOUT = 0;

    static {
        DEFAULT_HEADERS.put("Accept-Language", "en");
        DEFAULT_HEADERS.put("Accept-Encoding", "deflate, gzip");
        DEFAULT_HEADERS.put("User-Agent", "Chrome/68.0.3440.106");
        DEFAULT_HEADERS.put("Accept-Charset", "UTF-8");
    }

    private final Map<String, String> headers;
    private final int connectTimeout;
    private final int readTimeout;

    public HttpExecutor() {
        this(CONNECT_TIMEOUT);
    }

    public HttpExecutor(final int connectTimeout) {
        this(connectTimeout, READ_TIMEOUT);
    }

    public HttpExecutor(final int connectTimeout, final int readTimeout) {
        this(connectTimeout, readTimeout, DEFAULT_HEADERS);
    }

    /**
     * @param connectTimeout custom connection establish timeout in millis
     * @param readTimeout    custom read timeout in millis
     * @param headers        custom HTTP headers
     */
    public HttpExecutor(final int connectTimeout,
                        final int readTimeout,
                        final Map<String, String> headers) {
        this.connectTimeout = (connectTimeout < 0) ? 0 : connectTimeout;
        this.readTimeout = (readTimeout < 0) ? 0 : readTimeout;
        this.headers = headers;
    }

    private HttpURLConnection buildConnection(String urlAsString, String method) throws IOException {
        final URL url = new URL(urlAsString);
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setConnectTimeout(connectTimeout);
        connection.setReadTimeout(readTimeout);
        headers.forEach(connection::setRequestProperty);
        return connection;
    }

    @Override
    public String get(final String urlAsString) {
        try {
            final HttpURLConnection connection = buildConnection(urlAsString, "GET");
            final int status = connection.getResponseCode();
            if (status == HTTP_MOVED_TEMP || status == HTTP_MOVED_PERM) {
                return get(connection.getHeaderField("Location"));
            }

            final String data = readData(connection);
            connection.disconnect();
            return data;
        } catch (SocketTimeoutException e) {
            throw new ApiTimeoutException("Timeout: Could not establish connection for " + connectTimeout + " millis", e);
        } catch (Exception e) {
            throw new ConnectionException(e.getLocalizedMessage(), e);
        }
    }

    @Override
    public String post(final String urlAsString, final String dataToPost) {
        try {
            final HttpURLConnection connection = buildConnection(urlAsString, "POST");
            final String contentLength = (StringUtils.isEmpty(dataToPost)) ? "0" : String.valueOf(dataToPost.length());
            connection.setRequestProperty("content-length", contentLength);

            connection.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(dataToPost);
            wr.flush();
            wr.close();

            final int status = connection.getResponseCode();
            if (status == HTTP_MOVED_TEMP || status == HTTP_MOVED_PERM) {
                return post(connection.getHeaderField("Location"), dataToPost);
            }

            final String data = readData(connection);
            connection.disconnect();
            return data;
        } catch (SocketTimeoutException e) {
            throw new ApiTimeoutException("Timeout: Could not establish connection for " + connectTimeout + " millis", e);
        } catch (Exception e) {
            throw new ConnectionException(e.getLocalizedMessage(), e);
        }
    }

    private String readData(final HttpURLConnection connection) throws IOException {
        final StringBuilder content = new StringBuilder();
        try (BufferedReader in = new BufferedReader(getStreamReader(connection))) {
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                content.append(inputLine);

            in.close();
        }

        return content.toString();
    }

    private InputStreamReader getStreamReader(final HttpURLConnection connection) throws IOException {
        final boolean haveEncoding = connection.getContentEncoding() != null;

        if (haveEncoding && "gzip".equals(connection.getContentEncoding()))
            return new InputStreamReader(new GZIPInputStream(connection.getInputStream()), "utf-8");
        else if (haveEncoding && "deflate".equals(connection.getContentEncoding()))
            return new InputStreamReader(new InflaterInputStream(connection.getInputStream()), "utf-8");
        else
            return new InputStreamReader(connection.getInputStream(), "utf-8");
    }
}
