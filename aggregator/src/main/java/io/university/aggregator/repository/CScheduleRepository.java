package io.university.aggregator.repository;

import io.university.aggregator.model.dao.CSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Repository
public interface CScheduleRepository extends JpaRepository<CSchedule, String> {

}
