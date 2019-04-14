package io.university.oracle.repository;

import io.university.oracle.model.dao.OGrade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Repository
public interface OGradeRepository extends JpaRepository<OGrade, String> {

}
