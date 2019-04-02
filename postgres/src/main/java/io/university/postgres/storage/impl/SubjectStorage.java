package io.university.postgres.storage.impl;

import io.university.api.storage.impl.BasicJpaStorage;
import io.university.postgres.model.dao.Subject;
import io.university.postgres.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Component
public class SubjectStorage extends BasicJpaStorage<Subject, Integer> {

    @Autowired
    public SubjectStorage(final SubjectRepository repository) {
        super(repository);
    }
}
