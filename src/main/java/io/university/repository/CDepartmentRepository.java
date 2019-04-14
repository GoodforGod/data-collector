package io.university.repository;

import io.university.model.CDepartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Repository
public interface CDepartmentRepository extends JpaRepository<CDepartment, String> {

}
