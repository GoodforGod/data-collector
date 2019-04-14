package io.university.aggregator.storage.impl;

import io.university.aggregator.model.dao.CStudy;
import io.university.aggregator.repository.CStudyRepository;
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
public class CStudyStorage extends BasicJpaStorage<CStudy, String> {

    @Autowired
    public CStudyStorage(final CStudyRepository repository) {
        super(repository);
    }
}
