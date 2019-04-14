package io.university.storage.impl;

import io.university.model.CBook;
import io.university.repository.CBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 10.03.2019
 */
@Component
public class CBookStorage extends BasicJpaStorage<CBook, String> {

    @Autowired
    public CBookStorage(final CBookRepository repository) {
        super(repository);
    }
}
