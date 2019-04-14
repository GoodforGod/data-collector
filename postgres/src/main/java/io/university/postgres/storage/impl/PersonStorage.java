package io.university.postgres.storage.impl;

import io.university.api.storage.impl.BasicJpaStorage;
import io.university.postgres.model.dao.Person;
import io.university.postgres.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Component
public class PersonStorage extends BasicJpaStorage<Person, Integer> {

    private final PersonRepository personRepository;

    @Autowired
    public PersonStorage(final PersonRepository repository) {
        super(repository);
        this.personRepository = repository;
    }

    public List<Person> findByFullName(final String name,
                                       final String middleName,
                                       final String surname
    ) {
        return personRepository.findByNameAndMiddleNameAndSurname(name, middleName, surname);
    }

    public List<Person> findByBirth(final String birthPlace,
                                    final Timestamp birthTimestamp
    ) {
        return personRepository.findByBirthPlaceAndBirthTimestamp(birthPlace, birthTimestamp);
    }

    public Optional<Person> findByFullNameAndBirth(final String name,
                                                   final String middleName,
                                                   final String surname,
                                                   final String birthPlace,
                                                   final Timestamp birthTimestamp
    ) {
        return Optional.ofNullable(
                personRepository.findByNameAndMiddleNameAndSurnameAndBirthPlaceAndBirthTimestamp(name,
                        middleName,
                        surname,
                        birthPlace,
                        birthTimestamp)
        );
    }
}
