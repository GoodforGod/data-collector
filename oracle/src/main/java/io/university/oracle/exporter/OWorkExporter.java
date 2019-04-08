package io.university.oracle.exporter;

import io.university.api.exporter.BasicExporter;
import io.university.oracle.model.dao.OWorkHistory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.04.2019
 */
@Component
public class OWorkExporter extends BasicExporter<OWorkHistory> {

    @Value("${EXPORT_SERVER}")
    private String baseUrl;

    private final String modelEndpoint = "/postgres_oracle/update/work";

    @Override
    protected String getUrl() {
        return baseUrl + modelEndpoint;
    }
}
