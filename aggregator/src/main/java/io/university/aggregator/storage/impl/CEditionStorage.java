package io.university.aggregator.storage.impl;

import io.university.aggregator.model.dao.mysql.CEdition;
import io.university.aggregator.repository.CEditionRepository;
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
public class CEditionStorage extends BasicJpaStorage<CEdition, String> {

    @Autowired
    public CEditionStorage(final CEditionRepository repository) {
        super(repository);
    }
}
