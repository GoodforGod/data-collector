package io.university.aggregator.storage.impl;

import io.university.aggregator.model.dao.mysql.CPublishment;
import io.university.aggregator.repository.CPublishmentRepository;
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
public class CPublishmentStorage extends BasicJpaStorage<CPublishment, Integer> {

    @Autowired
    public CPublishmentStorage(final CPublishmentRepository repository) {
        super(repository);
    }
}
