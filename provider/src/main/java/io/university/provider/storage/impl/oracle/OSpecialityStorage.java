package io.university.provider.storage.impl.oracle;

import io.university.provider.model.dao.OSpeciality;
import io.university.provider.repository.OSpecialityRepository;
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
public class OSpecialityStorage extends BasicJpaStorage<OSpeciality, Integer> {

    @Autowired
    public OSpecialityStorage(final OSpecialityRepository repository) {
        super(repository);
    }
}
