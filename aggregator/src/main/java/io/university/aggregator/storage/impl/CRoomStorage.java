package io.university.aggregator.storage.impl;

import io.university.aggregator.dao.CRoom;
import io.university.aggregator.repository.CRoomRepository;
import io.university.api.storage.impl.BasicJpaStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 11.03.2019
 */
@Component
public class CRoomStorage extends BasicJpaStorage<CRoom, Integer> {

    @Autowired
    public CRoomStorage(final CRoomRepository repository) {
        super(repository);
    }
}
