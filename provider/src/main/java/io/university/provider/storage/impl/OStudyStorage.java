package io.university.provider.storage.impl;

import io.university.provider.model.dao.OStudy;
import io.university.provider.repository.OStudyRepository;
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
