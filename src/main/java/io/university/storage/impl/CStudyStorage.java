package io.university.storage.impl;

import io.university.model.dao.CStudy;
import io.university.repository.CStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Component
public class CStudyStorage extends BasicJpaStorage<CStudy, String> {

    @Autowired
    public CStudyStorage(final CStudyRepository repository) {
        super(repository);
    }
}
