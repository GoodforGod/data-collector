package io.university.api.exporter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.university.api.executor.HttpExecutor;
import io.university.api.executor.IHttpExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 07.04.2019
 */
@Service
public abstract class BasicExporter<T> implements IExporter<T> {

    private static final Logger logger = LoggerFactory.getLogger(BasicExporter.class);

    @Autowired
    private ObjectMapper mapper;

    @Value("${EXPORT_ENABLED:false}")
    private Boolean isExportEnabled;

    private final IHttpExecutor executor = new HttpExecutor();

    protected abstract String getUrl();

    @Override
    public T export(T t) {
        try {
            final String json = mapper.writeValueAsString(t);
            final String response = executor.post(getUrl(), json);
            if(StringUtils.isEmpty(response))
                logger.warn("Response is invalid!");

            return t;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public T exportIfPossible(T t) {
        if(isExportEnabled)
            export(t);
        return t;
    }
}
