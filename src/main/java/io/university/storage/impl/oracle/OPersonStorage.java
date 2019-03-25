package io.university.storage.impl.oracle;

import io.university.model.dao.oracle.OPerson;
import io.university.repository.oracle.OPersonRepository;
import io.university.storage.impl.BasicJpaStorage;
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
public class OPersonStorage extends BasicJpaStorage<OPerson, Integer> {

    private final OPersonRepository personRepository;

    @Autowired
    public OPersonStorage(final OPersonRepository repository) {
        super(repository);
        this.personRepository = repository;
    }

    public List<OPerson> findByFullName(final String name,
                                        final String middleName,
                                        final String surname
    ) {
        return personRepository.findByNameAndMiddleNameAndSurname(name, middleName, surname);
    }

    public List<OPerson> findByBirth(final String birthPlace,
                                     final Timestamp birthTimestamp
    ) {
        return personRepository.findByBirthPlaceAndBirthTimestamp(birthPlace, birthTimestamp);
    }

    public Optional<OPerson> findByFullNameAndBirth(final String name,
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
