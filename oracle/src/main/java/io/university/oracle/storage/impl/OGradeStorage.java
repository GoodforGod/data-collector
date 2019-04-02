package io.university.oracle.storage.impl;

import io.university.api.storage.impl.BasicJpaStorage;
import io.university.oracle.model.dao.OGrade;
import io.university.oracle.repository.OGradeRepository;
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
