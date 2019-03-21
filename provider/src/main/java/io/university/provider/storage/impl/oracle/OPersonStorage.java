package io.university.provider.storage.impl.oracle;

import io.university.provider.model.dao.OPerson;
import io.university.provider.repository.OPersonRepository;
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
public class OPersonStorage extends BasicJpaStorage<OPerson, Integer> {

    @Autowired
    public OPersonStorage(final OPersonRepository repository) {
        super(repository);
    }
}
