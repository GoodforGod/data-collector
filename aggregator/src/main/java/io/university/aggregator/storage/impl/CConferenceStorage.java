package io.university.aggregator.storage.impl;

import io.university.aggregator.dao.CConference;
import io.university.aggregator.repository.CConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 10.03.2019
 */
@Component
public class CConferenceStorage extends BasicJpaStorage<CConference, Integer> {

    @Autowired
    public CConferenceStorage(final CConferenceRepository repository) {
        super(repository);
    }
}
