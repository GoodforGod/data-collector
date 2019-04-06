package io.university.api.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.university.api.service.factory.IFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
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
public abstract class BasicDatabaseController<T> {

    private static final Logger logger = LoggerFactory.getLogger(BasicDatabaseController.class);
    private static final DateFormat ISO_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    private final IFactory<T> factory;
    private final ObjectMapper jsonMapper;
    private final TypeReference<List<T>> reference;

    public BasicDatabaseController(IFactory<T> factory) {
        this(factory, ISO_DATE_FORMAT);
    }

    public BasicDatabaseController(IFactory<T> factory, DateFormat dateFormat) {
        this.factory = factory;
        this.reference = new TypeReference<List<T>>() { };

        this.jsonMapper = new ObjectMapper();
        this.jsonMapper.setDateFormat(dateFormat);
    }

    /**
     * Filters CPerson entities parts to emulate only data for
     * Specific database only
     */
    protected abstract List<T> filterOtherDatabases(final List<T> list);

    /**
     * Emulate json serialisation and deserialization
     */
    protected List<T> generateAsJson(final Integer amount) {
        if (amount == null || amount < 1)
            return Collections.emptyList();

        final List<T> list = generateValid(amount);
        return transform(list);
    }

    protected List<T> transform(final List<T> list) {
        try {
            final String json = jsonMapper.writeValueAsString(list);
            return jsonMapper.readValue(json, reference);
        } catch (IOException e) {
            logger.warn(e.getMessage());
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    /**
     * Generates people with correct links between objects
     */
    protected List<T> generateValid(final int amount) {
        final List<T> people = factory.build(amount);
        return filterOtherDatabases(people);
    }
}
