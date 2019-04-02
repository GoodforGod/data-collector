package io.university.postgres.storage.impl;

import io.university.postgres.model.dao.OSpeciality;
import io.university.postgres.repository.OSpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Component
public class OSpecialityStorage extends BasicJpaStorage<OSpeciality, Integer> {

    @Autowired
    public OSpecialityStorage(final OSpecialityRepository repository) {
        super(repository);
    }
}
