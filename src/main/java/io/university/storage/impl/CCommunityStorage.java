package io.university.storage.impl;

import io.university.model.dao.CCommunity;
import io.university.repository.CCommunityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 11.03.2019
 */
@Component
public class CCommunityStorage extends BasicJpaStorage<CCommunity, String> {

    @Autowired
    public CCommunityStorage(final CCommunityRepository repository) {
        super(repository);
    }
}
