package io.university.storage.impl;

import io.university.model.dao.CConference;
import io.university.repository.CConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 10.03.2019
 */
@Component
public class CConferenceStorage extends BasicJpaStorage<CConference, String> {

    @Autowired
    public CConferenceStorage(final CConferenceRepository repository) {
        super(repository);
    }
}
