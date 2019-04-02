package io.university.aggregator.storage.impl;

import io.university.aggregator.dao.CWorkHistory;
import io.university.aggregator.repository.CWorkRepository;
import io.university.api.storage.impl.BasicJpaStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Component
public class CWorkStorage extends BasicJpaStorage<CWorkHistory, Integer> {

    @Autowired
    public CWorkStorage(final CWorkRepository repository) {
        super(repository);
    }
}
