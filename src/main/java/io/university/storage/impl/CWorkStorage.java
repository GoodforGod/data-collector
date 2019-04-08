package io.university.storage.impl;

import io.university.model.dao.CWorkHistory;
import io.university.repository.CWorkRepository;
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
