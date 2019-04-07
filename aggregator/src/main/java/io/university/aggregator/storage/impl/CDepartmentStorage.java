package io.university.aggregator.storage.impl;

import io.university.aggregator.model.dao.CDepartment;
import io.university.aggregator.repository.CDepartmentRepository;
import io.university.api.storage.impl.BasicJpaStorage;
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
