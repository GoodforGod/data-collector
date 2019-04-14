package io.university.aggregator.storage.impl;

import io.university.aggregator.model.dao.CGrade;
import io.university.aggregator.repository.CGradeRepository;
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
public class CGradeStorage extends BasicJpaStorage<CGrade, String> {

    @Autowired
    public CGradeStorage(final CGradeRepository repository) {
        super(repository);
    }
}
