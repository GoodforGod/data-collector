package io.university.storage.impl;

import io.university.model.CRoom;
import io.university.repository.CRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 11.03.2019
 */
@Component
public class CRoomStorage extends BasicJpaStorage<CRoom, String> {

    @Autowired
    public CRoomStorage(final CRoomRepository repository) {
        super(repository);
    }
}
