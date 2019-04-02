package io.university.postgres.storage.impl;

import io.university.postgres.model.dao.OGrade;
import io.university.postgres.repository.OGradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Component
public class OGradeStorage extends BasicJpaStorage<OGrade, String> {

    @Autowired
    public OGradeStorage(final OGradeRepository repository) {
        super(repository);
    }
}
