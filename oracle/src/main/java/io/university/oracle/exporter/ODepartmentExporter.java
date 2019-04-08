package io.university.oracle.exporter;

import io.university.api.exporter.BasicExporter;
import io.university.oracle.model.dao.ODepartment;
import org.springframework.beans.factory.annotation.Value;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.04.2019
 */
public class ODepartmentExporter extends BasicExporter<ODepartment> {

    @Value("${EXPORT_SERVER}")
    private String baseUrl;

    private final String modelEndpoint = "/postgres_oracle/update/grade";

    @Override
    protected String getUrl() {
        return baseUrl + modelEndpoint;
    }
}
