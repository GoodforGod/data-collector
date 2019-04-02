package io.university.provider.storage.impl;

import io.university.provider.model.dao.ODepartment;
import io.university.provider.repository.ODepartmentRepository;
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
