package io.university.aggregator.storage.impl;

import io.university.aggregator.dao.CCommunity;
import io.university.aggregator.repository.CCommunityRepository;
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
public class CCommunityStorage extends BasicJpaStorage<CCommunity, Integer> {

    @Autowired
    public CCommunityStorage(final CCommunityRepository repository) {
        super(repository);
    }
}
