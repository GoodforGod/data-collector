package io.university.storage.impl;

import io.university.model.CEdition;
import io.university.repository.CEditionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 10.03.2019
 */
@Component
public class CEditionStorage extends BasicJpaStorage<CEdition, String> {

    @Autowired
    public CEditionStorage(final CEditionRepository repository) {
        super(repository);
    }
}
