package io.university.repository;

import io.university.model.dao.CPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Repository
public interface CPersonRepository extends JpaRepository<CPerson, Integer> {

    List<CPerson> findByNameAndMiddleNameAndSurname(
            @Param("name") String name,
            @Param("middleName") String middleName,
            @Param("surname") String surname
    );

    List<CPerson> findByBirthPlaceAndBirthTimestamp(
            @Param("birthPlace") String birthPlace,
            @Param("birthTimestamp") Timestamp birthTimestamp
    );

    CPerson findByNameAndMiddleNameAndSurnameAndBirthPlaceAndBirthTimestamp(
            @Param("name") String name,
            @Param("middleName") String middleName,
            @Param("surname") String surname,
            @Param("birthPlace") String birthPlace,
            @Param("birthTimestamp") Timestamp birthTimestamp
    );
}
