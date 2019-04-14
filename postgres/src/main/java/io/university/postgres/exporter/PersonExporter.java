package io.university.postgres.exporter;

import io.university.api.exporter.BasicExporter;
import io.university.postgres.model.dao.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 07.04.2019
 */
@Component
public class PersonExporter extends BasicExporter<Person> {

    @Value("${EXPORT_SERVER}")
    private String baseUrl;

    private final String modelEndpoint = "/postgres_oracle/update/person";

    @Override
    protected String getUrl() {
        return baseUrl + modelEndpoint;
    }
}
