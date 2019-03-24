package io.university.storage.impl.common;

import io.university.model.dao.common.CLiving;
import io.university.repository.common.CLivingRepository;
import io.university.storage.impl.BasicJpaStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 11.03.2019
 */
@Component
public class CLivingStorage extends BasicJpaStorage<CLiving, String> {

    @Autowired
    public CLivingStorage(final CLivingRepository repository) {
        super(repository);
    }
}
