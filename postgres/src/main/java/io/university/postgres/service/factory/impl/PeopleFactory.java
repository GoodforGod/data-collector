package io.university.postgres.service.factory.impl;

import io.university.api.service.factory.impl.BasicFactory;
import io.university.postgres.model.dao.Person;
import io.university.postgres.model.dao.Speciality;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 01.03.2019
 */
@Service
public class PeopleFactory extends BasicFactory<Person> {

    private enum Ratio {
        SPECIALITY(30);

        private final int value;

        Ratio(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public int getRatio(int n) {
            return n < value ? 1 : n / value;
        }
    }

    @Override
    public Person build() {
        return build(1).get(0);
    }

    @Override
    public List<Person> build(int n) {
        final List<Speciality> specialities = factory.produce(Speciality.class, Ratio.SPECIALITY.getRatio(n));
        final List<Person> people = factory.produce(Person.class, n);

        specialities.forEach(s -> s.getSubjects().forEach(subject -> {
            subject.getGrades().forEach(g -> {
                final Person person = randomPick(people);
                g.setSubject(subject);
                person.addGrade(g);
            });
        }));

        for (final Person p : people) {
            final Speciality speciality = randomPick(specialities);
            p.getStudy().setSpeciality(speciality);
        }

        return people;
    }
}
