package io.university.provider.repository;

import io.university.provider.model.dao.OSpeciality;
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