package io.university.oracle.storage.impl;

import io.university.oracle.model.dao.OSchedule;
import io.university.oracle.repository.OScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Component
public class OScheduleStorage extends BasicJpaStorage<OSchedule, Integer> {

    @Autowired
    public OScheduleStorage(final OScheduleRepository repository) {
        super(repository);
    }
}
