package io.university.oracle.exporter;

import io.university.api.exporter.BasicExporter;
import io.university.oracle.model.dao.OGrade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.04.2019
 */
@Component
public class OGradeExporter extends BasicExporter<OGrade> {

    @Value("${EXPORT_SERVER}")
    private String baseUrl;

    private final String modelEndpoint = "/postgres_oracle/update/grade";

    @Override
    protected String getUrl() {
        return baseUrl + modelEndpoint;
    }
}
