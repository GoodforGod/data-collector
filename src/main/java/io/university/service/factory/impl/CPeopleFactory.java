package io.university.service.factory.impl;

import io.university.model.dao.*;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 13.03.2019
 */
@Service
public class CPeopleFactory extends BasicFactory<CPerson> {

    private enum Ratio {
        DEPARTMENT(30),
        CONFERENCE(20),
        EDITION(10),
        PROJECT(20),
        BOOKS(30),
        COMMUNITY(30),
        SPECIALITY(30);

        private final int value;

        Ratio(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public int getRatio(int n) {
            return n < value ? 1 : (n / value + 1);
        }
    }

    @Override
    public CPerson build() {
        return build(1).get(0);
    }

    @Override
    public List<CPerson> build(int n) {
        final List<CDepartment> departments = factory.produce(CDepartment.class, Ratio.DEPARTMENT.getRatio(n));
        final List<CSpeciality> specialities = factory.produce(CSpeciality.class, Ratio.SPECIALITY.getRatio(n));
        final List<CPerson> people = factory.produce(CPerson.class, n);

        specialities.forEach(s -> s.getSubjects().forEach(subject -> {
            subject.setSpeciality(s);
            subject.getGrades().forEach(g -> {
                final CPerson person = randomPick(people);
                g.setSubject(subject);
                person.addGrade(g);
            });
        }));

        for (final CPerson p : people) {
            final CSpeciality speciality = randomPick(specialities);

            p.getStudy().setPerson(p);
            p.getStudy().setSpeciality(speciality);
            p.getWorkHistory().setPerson(p);

            final CDepartment department = randomPick(departments);
            p.getStudy().setDepartment(department);
            p.getWorkHistory().setDepartment(department);
        }

        final List<CSubject> subjects = specialities.stream()
                .filter(s -> !CollectionUtils.isEmpty(s.getSubjects()))
                .map(CSpeciality::getSubjects)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        final List<CSchedule> schedules = factory.produce(CSchedule.class, subjects.size());
        for (int i = 0; i < subjects.size(); i++) {
            final CSchedule schedule = schedules.get(i);
            subjects.get(i).setSchedule(schedule);
            schedule.setSubject(subjects.get(i));
        }

        for (final CPerson p : people) {
            final CSpeciality speciality = randomPick(specialities);
            speciality.getSubjects().stream()
                    .filter(s -> s.getSchedule() != null)
                    .forEach(s -> p.addSchedule(s.getSchedule()));
        }

        final List<CConference> conferences = factory.produce(CConference.class, Ratio.CONFERENCE.getRatio(n));
        for (CConference conference : conferences) {
            final CPerson person = randomPick(people);
            person.addConference(conference);
        }

        final List<CBook> books = factory.produce(CBook.class, Ratio.BOOKS.getRatio(n));
        for (CBook book : books) {
            for (CReading reading : book.getReadings()) {
                final CPerson person = randomPick(people);
                reading.setBook(book);
                person.addReading(reading);
                reading.setPerson(person);
            }
        }

        final List<CProject> projects = factory.produce(CProject.class, Ratio.PROJECT.getRatio(n));
        for (CProject project : projects) {
            for (CProjectParticipation participation : project.getParticipations()) {
                final CPerson person = randomPick(people);
                participation.setProject(project);
                person.addParticipation(participation);
                participation.setPerson(person);
            }
        }

        final List<CEdition> editions = factory.produce(CEdition.class, Ratio.EDITION.getRatio(n));
        for (CEdition edition : editions) {
            for (CPublishment publishment : edition.getPublishments()) {
                final CPerson person = randomPick(people);
                publishment.setEdition(edition);
                publishment.setPerson(person);
                person.addPublishment(publishment);
            }
        }

        final List<CCommunity> communities = factory.produce(CCommunity.class, Ratio.COMMUNITY.getRatio(n));
        for (CCommunity community : communities) {
            for (CRoom room : community.getRooms()) {
                room.setCommunity(community);
                for (CLiving living : room.getLivings()) {
                    final CPerson person = randomPick(people);
                    living.setPerson(person);
                    living.setRoom(room);
                    person.addLiving(living);
                }
            }

            for (CVisit visit : community.getVisits()) {
                final CPerson person = randomPick(people);
                visit.setPerson(person);
                visit.setCommunity(community);
                person.addVisit(visit);
            }
        }

        return people;
    }
}
