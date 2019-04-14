package io.university.oracle.repository;

import io.university.oracle.model.dao.OPerson;
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
public interface OPersonRepository extends JpaRepository<OPerson, Integer> {

    List<OPerson> findByNameAndMiddleNameAndSurname(
            @Param("name") String name,
            @Param("middleName") String middleName,
            @Param("surname") String surname
    );

    List<OPerson> findByBirthPlaceAndBirthTimestamp(
            @Param("birthPlace") String birthPlace,
            @Param("birthTimestamp") Timestamp birthTimestamp
    );

    OPerson findByNameAndMiddleNameAndSurnameAndBirthPlaceAndBirthTimestamp(
            @Param("name") String name,
            @Param("middleName") String middleName,
            @Param("surname") String surname,
            @Param("birthPlace") String birthPlace,
            @Param("birthTimestamp") Timestamp birthTimestamp
    );
}
