package io.university.postgres.exporter;

import io.university.api.exporter.BasicExporter;
import io.university.postgres.model.dao.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 09.04.2019
 */
@Component
public class PeopleExporter extends BasicExporter<List<Person>> {

    @Value("${EXPORT_SERVER}")
    private String baseUrl;

    private final String modelEndpoint = "/common/postgres/load";

    @Override
    protected String getUrl() {
        return baseUrl + modelEndpoint;
    }
}
