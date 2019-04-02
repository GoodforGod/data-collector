package io.university.postgres.repository;

import io.university.postgres.model.dao.Person;
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
public interface PersonRepository extends JpaRepository<Person, Integer> {

    List<Person> findByNameAndMiddleNameAndSurname(
            @Param("name") String name,
            @Param("middleName") String middleName,
            @Param("surname") String surname
    );

    List<Person> findByBirthPlaceAndBirthTimestamp(
            @Param("birthPlace") String birthPlace,
            @Param("birthTimestamp") Timestamp birthTimestamp
    );

    Person findByNameAndMiddleNameAndSurnameAndBirthPlaceAndBirthTimestamp(
            @Param("name") String name,
            @Param("middleName") String middleName,
            @Param("surname") String surname,
            @Param("birthPlace") String birthPlace,
            @Param("birthTimestamp") Timestamp birthTimestamp
    );
}
