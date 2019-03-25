package io.university.repository.common;

import io.university.model.dao.common.CVisit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 11.03.2019
 */
@Repository
public interface CVisitRepository extends JpaRepository<CVisit, String> {

}
