package io.university.storage.impl;

import io.university.model.CReading;
import io.university.repository.CReadingRepository;
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
