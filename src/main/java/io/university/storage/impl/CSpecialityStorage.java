package io.university.storage.impl;

import io.university.model.CSpeciality;
import io.university.repository.CSpecialityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Component
public class CSpecialityStorage extends BasicJpaStorage<CSpeciality, Integer> {

    @Autowired
    public CSpecialityStorage(final CSpecialityRepository repository) {
        super(repository);
    }
}
