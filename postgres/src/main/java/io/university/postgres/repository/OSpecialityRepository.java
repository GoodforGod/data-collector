package io.university.postgres.repository;

import io.university.postgres.model.dao.OSpeciality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Repository
public interface OSpecialityRepository extends JpaRepository<OSpeciality, Integer> {

}
