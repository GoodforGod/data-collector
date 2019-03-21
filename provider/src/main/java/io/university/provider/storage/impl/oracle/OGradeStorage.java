package io.university.provider.storage.impl.oracle;

import io.university.provider.model.dao.OGrade;
import io.university.provider.repository.OGradeRepository;
import io.university.provider.storage.impl.BasicJpaStorage;
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
