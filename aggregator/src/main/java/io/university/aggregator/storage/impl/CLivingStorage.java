package io.university.aggregator.storage.impl;

import io.university.aggregator.model.dao.mongo.CLiving;
import io.university.aggregator.repository.CLivingRepository;
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
public class CLivingStorage extends BasicJpaStorage<CLiving, String> {

    @Autowired
    public CLivingStorage(final CLivingRepository repository) {
        super(repository);
    }
}
