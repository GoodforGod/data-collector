package io.university.oracle.exporter;

import io.university.api.exporter.BasicExporter;
import io.university.oracle.model.dao.OSpeciality;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.04.2019
 */
@Component
public class OSpecialityExporter extends BasicExporter<OSpeciality> {

    @Value("${EXPORT_SERVER}")
    private String baseUrl;

    private final String modelEndpoint = "/postgres_oracle/update/speciality";

    @Override
    protected String getUrl() {
        return baseUrl + modelEndpoint;
    }
}
