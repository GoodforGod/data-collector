package io.university.storage.impl;

import io.university.model.CSchedule;
import io.university.repository.CScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Component
public class CScheduleStorage extends BasicJpaStorage<CSchedule, String> {

    @Autowired
    public CScheduleStorage(final CScheduleRepository repository) {
        super(repository);
    }
}