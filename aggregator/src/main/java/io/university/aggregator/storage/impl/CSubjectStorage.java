package io.university.aggregator.storage.impl;

import io.university.aggregator.model.dao.CSubject;
import io.university.aggregator.repository.CSubjectRepository;
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
public class CSubjectStorage extends BasicJpaStorage<CSubject, Integer> {

    @Autowired
    public CSubjectStorage(final CSubjectRepository repository) {
        super(repository);
    }
}
