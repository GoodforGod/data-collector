package io.university.controller.common.aggregator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.university.model.dao.common.CPerson;
import io.university.service.factory.impl.CPeopleFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.03.2019
 */
abstract class BasicDatabaseController {

    private static final Logger logger = LoggerFactory.getLogger(BasicDatabaseController.class);

    private final CPeopleFactory factory;
    private final ObjectMapper jsonMapper;
    private final TypeReference<List<CPerson>> reference;

    BasicDatabaseController(CPeopleFactory factory) {
        this.factory = factory;
        this.jsonMapper = new ObjectMapper();
        this.reference = new TypeReference<List<CPerson>>() { };
    }

    /**
     * Filters CPerson entities parts to emulate only data for
     * Specific database only
     */
    abstract List<CPerson> filterOtherDatabases(final List<CPerson> list);

    /**
     * Emulate json serialisation and deserialization
     */
    List<CPerson> generateAsJson(final Integer amount) {
        if(amount == null || amount < 1)
            return Collections.emptyList();

        try {
            final List<CPerson> people = generateValid(amount);
            final String json = jsonMapper.writeValueAsString(people);
            return jsonMapper.readValue(json, reference);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Generates people with correct links between objects
     */
    List<CPerson> generateValid(final int amount) {
        final List<CPerson> people = factory.build(amount);
        return filterOtherDatabases(people);
    }
}
