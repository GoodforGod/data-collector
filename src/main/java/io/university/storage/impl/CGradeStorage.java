package io.university.storage.impl;

import io.university.model.CGrade;
import io.university.repository.CGradeRepository;
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
