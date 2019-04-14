package io.university.storage.impl;

import io.university.model.CDepartment;
import io.university.repository.CDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Component
public class CDepartmentStorage extends BasicJpaStorage<CDepartment, String> {

    @Autowired
    public CDepartmentStorage(final CDepartmentRepository repository) {
        super(repository);
    }
}
