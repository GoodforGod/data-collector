package io.university.service.validator.impl;

import io.university.model.dao.*;
import io.university.storage.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 13.03.2019
 */
@Service
public class CPersonOracleValidator extends BasicCPersonValidator {

    @Autowired private CDepartmentStorage departmentStorage;
    @Autowired private CSpecialityStorage specialityStorage;
    @Autowired private CScheduleStorage scheduleStorage;
    @Autowired private CSubjectStorage subjectStorage;
    @Autowired private CPersonStorage peopleStorage;
    @Autowired private CStudyStorage studyStorage;
    @Autowired private CGradeStorage gradeStorage;

    @Override
    public List<CPerson> validate(List<CPerson> people) {
        if (CollectionUtils.isEmpty(people))
            return Collections.emptyList();

        final Map<Integer, CDepartment> departmentMap = new HashMap<>();
        final Map<Integer, CSpeciality> specialityMap = new HashMap<>();
        final Map<Integer, CSchedule> scheduleMap = new HashMap<>();
        final Map<Integer, CSubject> subjectMap = new HashMap<>();
        final Map<Integer, CStudy> studyMap = new HashMap<>();
        final Map<Integer, CGrade> gradeMap = new HashMap<>();

        final List<CPerson> validPeople = new ArrayList<>(people.size());

        for (CPerson p : people) {
            final CPerson validPerson = peopleStorage.findByFullNameAndBirth(
                    p.getName(),
                    p.getMiddleName(),
                    p.getSurname(),
                    p.getBirthPlace(),
                    p.getBirthTimestamp()).orElse(p);

            fillPersonStudy(p, departmentMap, specialityMap, studyMap, studyStorage, departmentStorage, specialityStorage);

            CWorkHistory history = p.getWorkHistory();
            if (history != null) {
                if (history.getDepartment() != null) {
                    CDepartment department = departmentMap.computeIfAbsent(history.getDepartment().hashCode(),
                            (k) -> departmentStorage.find(history.getDepartment().getId()).orElse(history.getDepartment()));
                    history.setDepartment(department);
                }

                validPerson.setWorkHistory(history);
                history.setPerson(validPerson);
            }

            if (!CollectionUtils.isEmpty(p.getSchedules())) {
                final Set<CSchedule> schedules = new HashSet<>();
                for (CSchedule schedule : p.getSchedules()) {
                    final CSchedule usedSchedule = scheduleMap.computeIfAbsent(schedule.hashCode(),
                            (k) -> scheduleStorage.find(schedule.getId()).orElse(schedule));

                    if (schedule.getSubject() == null) {
                        validPerson.addSchedule(usedSchedule);
                        continue;
                    }

                    CSubject subject = subjectMap.computeIfAbsent(schedule.getSubject().getCode(),
                            (k) -> subjectStorage.find(schedule.getSubject().getCode()).orElse(schedule.getSubject()));

                    if (subject != schedule.getSubject()) {
                        Set<CGrade> grades = schedule.getSubject().getGrades().stream()
                                .peek(g -> subject.getGrades().remove(g))
                                .collect(Collectors.toSet());
                        subject.getGrades().addAll(grades);
                    }

                    if (subject.getSpeciality() != null) {
                        CSpeciality speciality = getSpeciality(subject.getSpeciality(), specialityMap, specialityStorage);
                        fillSpecialityStudy(speciality, subject.getSpeciality().getStudy(), studyMap, studyStorage);
                        subject.setSpeciality(speciality);
                    }

                    usedSchedule.setSubject(subject);
                    schedules.add(usedSchedule);
                }
                validPerson.getSchedules().removeAll(schedules);
                validPerson.getSchedules().addAll(schedules);
            }

            if(!CollectionUtils.isEmpty(p.getGrades())) {
                for (CGrade grade : p.getGrades()) {
                    CSubject subject = subjectMap.computeIfAbsent(grade.getSubject().getCode(),
                            (k) -> subjectStorage.find(grade.getSubject().getCode()).orElse(grade.getSubject()));

                    grade.setPerson(validPerson);
                    grade.setSubject(subject);
                    subject.getGrades().remove(grade);
                    subject.addGrade(grade);
                }
            }

            validPerson.clearConference();
            validPerson.clearParticipation();
            validPerson.clearPublishment();
            validPerson.clearReadings();
            validPerson.clearLivings();
            validPerson.clearVisits();

            validPeople.add(validPerson);
        }

        return validPeople;
    }
}
