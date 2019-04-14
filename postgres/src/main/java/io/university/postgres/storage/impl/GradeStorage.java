package io.university.postgres.storage.impl;

import io.university.api.storage.impl.BasicJpaStorage;
import io.university.postgres.model.dao.Grade;
import io.university.postgres.repository.GradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Component
public class GradeStorage extends BasicJpaStorage<Grade, String> {

    @Autowired
    public GradeStorage(final GradeRepository repository) {
        super(repository);
    }
}
