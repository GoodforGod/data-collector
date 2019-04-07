package io.university.api.executor;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 07.04.2019
 */
public interface IHttpExecutor {

    /**
     * Performs a Http GET request
     *
     * @param url as string
     * @return result as string
     */
    String get(String url);

    /**
     * Performs a Http POST request
     *
     * @param url as string
     * @param data to post
     * @return result as string
     */
    String post(String url, String data);
}
