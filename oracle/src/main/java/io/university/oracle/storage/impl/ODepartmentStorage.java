package io.university.oracle.storage.impl;

import io.university.api.storage.impl.BasicJpaStorage;
import io.university.oracle.model.dao.ODepartment;
import io.university.oracle.repository.ODepartmentRepository;
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
