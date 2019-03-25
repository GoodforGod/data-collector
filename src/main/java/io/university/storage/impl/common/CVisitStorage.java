package io.university.storage.impl.common;

import io.university.model.dao.common.CVisit;
import io.university.repository.common.CVisitRepository;
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
public class CVisitStorage extends BasicJpaStorage<CVisit, String> {

    @Autowired
    public CVisitStorage(final CVisitRepository repository) {
        super(repository);
    }
}
