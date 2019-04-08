package io.university.service.validator.impl;

import io.university.model.dao.*;
import io.university.storage.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 13.03.2019
 */
@Service
public class CPersonPostgresValidator extends BasicCPersonValidator {

    @Autowired private CDepartmentStorage departmentStorage;
    @Autowired private CSpecialityStorage specialityStorage;
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
        final Map<Integer, CSubject> subjectMap = new HashMap<>();
        final Map<Integer, CStudy> studyMap = new HashMap<>();

        final List<CPerson> validPeople = new ArrayList<>(people.size());

        for (CPerson p : people) {
            final CPerson existPerson = peopleStorage.findByFullNameAndBirth(
                    p.getName(),
                    p.getMiddleName(),
                    p.getSurname(),
                    p.getBirthPlace(),
                    p.getBirthTimestamp()).orElse(null);

            final boolean isExist = existPerson != null;
            if (isExist) {
                peopleStorage.deleteById(existPerson.getId());
            }

            if (p.getStudy() == null && isExist && existPerson.getStudy() != null) {
                p.setStudy(existPerson.getStudy());
            } else if (p.getStudy() != null) {
                // Set priority entity
                if (!studyMap.containsKey(p.getStudy().hashCode())) {
                    studyMap.put(p.getStudy().hashCode(), p.getStudy());
                }

                // Set priority entity
                CDepartment department = p.getStudy().getDepartment();
                if (department != null && !departmentMap.containsKey(department.hashCode())) {
                    departmentMap.put(department.hashCode(), department);
                }

                // Set priority entity
                CSpeciality speciality = p.getStudy().getSpeciality();
                if (speciality != null && !specialityMap.containsKey(speciality.hashCode())) {
                    specialityMap.put(speciality.hashCode(), speciality);
                }

                fillPersonStudy(p, departmentMap, specialityMap, studyMap, studyStorage, departmentStorage, specialityStorage);
            }

            p.setWorkHistory(null);
            p.getPublishments().clear();
            p.getPublishments().clear();
            p.getConferences().clear();
            p.getSchedules().clear();
            p.getReadings().clear();
            p.getLivings().clear();
            p.getVisits().clear();

            if (isExist) {
                if (!CollectionUtils.isEmpty(existPerson.getGrades())) {
                    existPerson.getGrades().forEach(p::addGrade);
                }

                final CWorkHistory history = existPerson.getWorkHistory();
                if (history != null) {
                    if (p.getStudy() != null) {
                        history.setDepartment(p.getStudy().getDepartment());
                    }

                    p.setWorkHistory(history);
                }

                if (existPerson.getWorkHistory() != null) {
                    p.setWorkHistory(existPerson.getWorkHistory());
                }

                if (!CollectionUtils.isEmpty(existPerson.getPublishments())) {
                    existPerson.getPublishments().forEach(p::addPublishment);
                }

                if (!CollectionUtils.isEmpty(existPerson.getConferences())) {
                    existPerson.getConferences().forEach(p::addConference);
                }

                if (!CollectionUtils.isEmpty(existPerson.getReadings())) {
                    existPerson.getReadings().forEach(p::addReading);
                }

                if (!CollectionUtils.isEmpty(existPerson.getParticipations())) {
                    existPerson.getParticipations().forEach(p::addParticipation);
                }

                if (!CollectionUtils.isEmpty(existPerson.getLivings())) {
                    existPerson.getLivings().forEach(p::addLiving);
                }

                if (!CollectionUtils.isEmpty(existPerson.getSchedules())) {
                    existPerson.getSchedules().forEach(p::addSchedule);
                }

                if (!CollectionUtils.isEmpty(existPerson.getVisits())) {
                    existPerson.getVisits().forEach(p::addVisit);
                }
            }

            if(!CollectionUtils.isEmpty(p.getGrades())) {
                for (CGrade grade : p.getGrades()) {
                    CSubject subject = subjectMap.computeIfAbsent(grade.getSubject().getCode(), (k) -> grade.getSubject());
                    CSpeciality speciality = getSpeciality(subject.getSpeciality(), specialityMap, specialityStorage);

                    speciality.addSubject(subject);
                    subject.setSpeciality(speciality);

                    grade.setSubject(subject);
                    grade.setPerson(p);
                }
            }

            validPeople.add(p);
        }

        return validPeople;
    }
}
