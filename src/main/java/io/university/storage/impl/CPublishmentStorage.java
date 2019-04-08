package io.university.storage.impl;

import io.university.model.dao.CPublishment;
import io.university.repository.CPublishmentRepository;
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
