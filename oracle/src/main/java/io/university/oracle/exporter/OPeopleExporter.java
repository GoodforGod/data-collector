package io.university.oracle.exporter;

import io.university.api.exporter.BasicExporter;
import io.university.oracle.model.dao.OPerson;
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
public class OPeopleExporter extends BasicExporter<List<OPerson>> {

    @Value("${EXPORT_SERVER}")
    private String baseUrl;

    private final String modelEndpoint = "/common/oracle/load";

    @Override
    protected String getUrl() {
        return baseUrl + modelEndpoint;
    }
}
