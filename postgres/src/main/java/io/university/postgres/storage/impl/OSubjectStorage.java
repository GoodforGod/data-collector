package io.university.postgres.storage.impl;

import io.university.postgres.model.dao.OSubject;
import io.university.postgres.repository.OSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Component
public class OSubjectStorage extends BasicJpaStorage<OSubject, Integer> {

    @Autowired
    public OSubjectStorage(final OSubjectRepository repository) {
        super(repository);
    }
}
