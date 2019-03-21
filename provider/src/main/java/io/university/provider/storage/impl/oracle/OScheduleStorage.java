package io.university.provider.storage.impl.oracle;

import io.university.provider.model.dao.OSchedule;
import io.university.provider.repository.OScheduleRepository;
import io.university.provider.storage.impl.BasicJpaStorage;
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
