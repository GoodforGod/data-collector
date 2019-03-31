package io.university.aggregator.storage.impl;

import io.university.aggregator.dao.CProject;
import io.university.aggregator.repository.CProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 10.03.2019
 */
@Component
public class CProjectStorage extends BasicJpaStorage<CProject, Integer> {

    @Autowired
    public CProjectStorage(final CProjectRepository repository) {
        super(repository);
    }
}
