package io.university.storage.impl;

import io.university.model.CProject;
import io.university.repository.CProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 10.03.2019
 */
@Component
public class CProjectStorage extends BasicJpaStorage<CProject, String> {

    @Autowired
    public CProjectStorage(final CProjectRepository repository) {
        super(repository);
    }
}
