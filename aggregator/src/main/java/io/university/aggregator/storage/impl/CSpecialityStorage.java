package io.university.aggregator.storage.impl;

import io.university.aggregator.dao.CSpeciality;
import io.university.aggregator.repository.CSpecialityRepository;
import io.university.api.storage.impl.BasicJpaStorage;
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
