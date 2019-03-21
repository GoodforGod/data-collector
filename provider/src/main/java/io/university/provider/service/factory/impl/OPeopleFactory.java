package io.university.provider.service.factory.impl;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 01.03.2019
 */
@Service
public class OPeopleFactory extends BasicFactory<OPerson> {

    private enum Ratio {
        DEPARTMENT(100),
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
    public OPerson build() {
        return build(1).get(0);
    }

    @Override
    public List<OPerson> build(int n) {
        final List<ODepartment> departments = factory.produce(ODepartment.class, Ratio.DEPARTMENT.getRatio(n));
        final List<OSpeciality> specialities = factory.produce(OSpeciality.class, Ratio.SPECIALITY.getRatio(n));
        final List<OPerson> people = factory.produce(OPerson.class, n);

        specialities.forEach(s -> s.getSubjects().forEach(subject -> {
            subject.setSpeciality(s);
            subject.getGrades().forEach(g -> {
                final OPerson person = people.get(ThreadLocalRandom.current().nextInt(0, people.size() - 1));
                g.setSubject(subject);
                person.addGrade(g);
            });
        }));

        for (int i = 0; i < people.size(); i++) {
            final OPerson p = people.get(i);
            final OSpeciality speciality = specialities.get(i / Ratio.SPECIALITY.value);

            p.getStudy().setPerson(p);
            p.getStudy().setSpeciality(speciality);
            p.getWorkHistory().setPerson(p);

            final ODepartment department = departments.get(i / Ratio.DEPARTMENT.value);
            p.getStudy().setDepartment(department);
            p.getWorkHistory().setDepartment(department);

        }

        final List<OSubject> subjects = specialities.stream()
                .filter(s -> CollectionUtils.isEmpty(s.getSubjects()))
                .map(OSpeciality::getSubjects)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        final List<OSchedule> schedules = factory.produce(OSchedule.class, subjects.size());
        for (int i = 0; i < subjects.size(); i++) {
            subjects.get(i).setSchedule(schedules.get(i));
        }

        for (int i = 0; i < people.size(); i++) {
            final OPerson p = people.get(i);
            final OSpeciality speciality = specialities.get(i / Ratio.SPECIALITY.value);
            speciality.getSubjects().forEach(s -> p.addSchedule(s.getSchedule()));
        }

        return people;
    }
}
