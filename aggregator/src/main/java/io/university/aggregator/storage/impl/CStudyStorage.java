package io.university.aggregator.storage.impl;

import io.university.aggregator.dao.CStudy;
import io.university.aggregator.repository.CStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Component
public class CStudyStorage extends BasicJpaStorage<CStudy, Integer> {

    @Autowired
    public CStudyStorage(final CStudyRepository repository) {
        super(repository);
    }
}
