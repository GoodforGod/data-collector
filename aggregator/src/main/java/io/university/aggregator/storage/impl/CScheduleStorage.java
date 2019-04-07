package io.university.aggregator.storage.impl;

import io.university.aggregator.model.dao.CSchedule;
import io.university.aggregator.repository.CScheduleRepository;
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
public class CScheduleStorage extends BasicJpaStorage<CSchedule, String> {

    @Autowired
    public CScheduleStorage(final CScheduleRepository repository) {
        super(repository);
    }
}
