package io.university.aggregator.repository;

import io.university.aggregator.dao.CCommunity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 11.03.2019
 */
@Repository
public interface CCommunityRepository extends JpaRepository<CCommunity, Integer> {

}
