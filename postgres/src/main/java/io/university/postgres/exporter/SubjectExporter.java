package io.university.postgres.exporter;

import io.university.api.exporter.BasicExporter;
import io.university.postgres.model.dao.Subject;
import org.springframework.beans.factory.annotation.Value;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.04.2019
 */
public class SubjectExporter extends BasicExporter<Subject> {

    @Value("${EXPORT_SERVER}")
    private String baseUrl;

    private final String modelEndpoint = "/common/postgres/load";

    @Override
    protected String getUrl() {
        return baseUrl + modelEndpoint;
    }
}
