package io.university.aggregator.storage.impl;

import io.university.aggregator.dao.CReading;
import io.university.aggregator.repository.CReadingRepository;
import io.university.api.storage.impl.BasicJpaStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 10.03.2019
 */
@Component
public class CReadingStorage extends BasicJpaStorage<CReading, Integer> {

    @Autowired
    public CReadingStorage(final CReadingRepository repository) {
        super(repository);
    }
}
