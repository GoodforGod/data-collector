package io.university.oracle.storage.impl;

import io.university.oracle.model.dao.OStudy;
import io.university.oracle.repository.OStudyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Component
public class OStudyStorage extends BasicJpaStorage<OStudy, Integer> {

    @Autowired
    public OStudyStorage(final OStudyRepository repository) {
        super(repository);
    }
}
