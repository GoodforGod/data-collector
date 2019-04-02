package io.university.postgres.storage.impl;

import io.university.api.storage.impl.BasicJpaStorage;
import io.university.postgres.model.dao.Study;
import io.university.postgres.repository.StudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Component
public class StudyStorage extends BasicJpaStorage<Study, Integer> {

    @Autowired
    public StudyStorage(final StudyRepository repository) {
        super(repository);
    }
}
