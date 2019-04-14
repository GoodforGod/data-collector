package io.university.storage.impl;

import io.university.model.CSubject;
import io.university.repository.CSubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Component
public class CSubjectStorage extends BasicJpaStorage<CSubject, Integer> {

    @Autowired
    public CSubjectStorage(final CSubjectRepository repository) {
        super(repository);
    }
}
