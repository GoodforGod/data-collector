package io.university.aggregator.storage.impl;

import io.university.aggregator.model.dao.mongo.CVisit;
import io.university.aggregator.repository.CVisitRepository;
import io.university.api.storage.impl.BasicJpaStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 11.03.2019
 */
@Component
public class CVisitStorage extends BasicJpaStorage<CVisit, String> {

    @Autowired
    public CVisitStorage(final CVisitRepository repository) {
        super(repository);
    }
}
