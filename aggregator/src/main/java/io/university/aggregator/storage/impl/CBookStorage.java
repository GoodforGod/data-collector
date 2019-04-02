package io.university.aggregator.storage.impl;

import io.university.aggregator.dao.CBook;
import io.university.aggregator.repository.CBookRepository;
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
public class CBookStorage extends BasicJpaStorage<CBook, Integer> {

    @Autowired
    public CBookStorage(final CBookRepository repository) {
        super(repository);
    }
}
