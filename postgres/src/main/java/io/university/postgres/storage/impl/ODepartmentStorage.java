package io.university.postgres.storage.impl;

import io.university.postgres.model.dao.ODepartment;
import io.university.postgres.repository.ODepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Component
public class ODepartmentStorage extends BasicJpaStorage<ODepartment, Integer> {

    @Autowired
    public ODepartmentStorage(final ODepartmentRepository repository) {
        super(repository);
    }
}
