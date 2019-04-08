package io.university.controller.aggregator;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.university.model.dao.CPerson;
import io.university.service.factory.IFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.03.2019
 */
public abstract class BasicDatabaseController {

    private static final Logger logger = LoggerFactory.getLogger(BasicDatabaseController.class);
    private static final DateFormat ISO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    private final IFactory<CPerson> factory;
    private final ObjectMapper jsonMapper;
    private final TypeReference<List<CPerson>> reference;

    public BasicDatabaseController(IFactory<CPerson> factory) {
        this(factory, ISO_DATE_FORMAT);
    }

    public BasicDatabaseController(IFactory<CPerson> factory, DateFormat dateFormat) {
        this.factory = factory;
        this.reference = new TypeReference<List<CPerson>>() { };

        this.jsonMapper = new ObjectMapper();
        this.jsonMapper.setDateFormat(dateFormat);
    }

    /**
     * Filters CPerson entities parts to emulate only data for
     * Specific database only
     */
    protected abstract List<CPerson> filterOtherDatabases(final List<CPerson> list);

    /**
     * Emulate json serialisation and deserialization
     */
    protected List<CPerson> generateAsJson(final Integer amount) {
        if(amount == null || amount < 1)
            return Collections.emptyList();

        try {
            final List<CPerson> people = generateValid(amount);
            final String json = jsonMapper.writeValueAsString(people);
            return jsonMapper.readValue(json, this.reference);
        } catch (Exception e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Generates people with correct links between objects
     */
    protected List<CPerson> generateValid(final int amount) {
        final List<CPerson> people = factory.build(amount);
        return filterOtherDatabases(people);
    }
}
