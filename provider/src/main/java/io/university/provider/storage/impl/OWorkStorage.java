package io.university.provider.storage.impl;

import io.university.provider.model.dao.OWorkHistory;
import io.university.provider.repository.OWorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Component
public class OWorkStorage extends BasicJpaStorage<OWorkHistory, Integer> {

    @Autowired
    public OWorkStorage(final OWorkRepository repository) {
        super(repository);
    }
}
