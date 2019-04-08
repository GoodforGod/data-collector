package io.university.service.validator.impl;

import io.university.model.dao.CDepartment;
import io.university.model.dao.CPerson;
import io.university.model.dao.CSpeciality;
import io.university.model.dao.CStudy;
import io.university.service.validator.IValidator;
import io.university.storage.impl.CDepartmentStorage;
import io.university.storage.impl.CSpecialityStorage;
import io.university.storage.impl.CStudyStorage;

import java.util.Map;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 25.03.2019
 */
abstract class BasicCPersonValidator implements IValidator<CPerson> {

    CSpeciality getSpeciality(CSpeciality speciality,
                              Map<Integer, CSpeciality> specialityMap,
                              CSpecialityStorage specialityStorage) {
        return specialityMap.computeIfAbsent(
                speciality.hashCode(),
                (k) -> specialityStorage.find(speciality.getCode()).orElse(speciality));
    }

    void fillSpecialityStudy(CSpeciality speciality,
                             CStudy primeStudy,
                             Map<Integer, CStudy> studyMap,
                             CStudyStorage studyStorage) {
        if (speciality.getStudy() != null) {
            CStudy cStudy = studyMap.computeIfAbsent(speciality.getStudy().hashCode(), (k) -> speciality.getStudy());
            speciality.setStudy(cStudy);
        } else if (primeStudy != null) {
            CStudy cStudy = studyMap.computeIfAbsent(primeStudy.hashCode(),
                    (k) -> studyStorage.find(primeStudy.getId()).orElse(primeStudy));
            speciality.setStudy(cStudy);
        }
    }

    void fillPersonStudy(CPerson p,
                         Map<Integer, CDepartment> departmentMap,
                         Map<Integer, CSpeciality> specialityMap,
                         Map<Integer, CStudy> studyMap,
                         CStudyStorage studyStorage,
                         CDepartmentStorage departmentStorage,
                         CSpecialityStorage specialityStorage) {
        if (p.getStudy() != null) {
            CStudy study = studyMap.computeIfAbsent(p.getStudy().hashCode(),
                    (k) -> studyStorage.find(p.getStudy().getId()).orElse(p.getStudy()));

            if (study.getDepartment() != null) {
                CDepartment department = departmentMap.computeIfAbsent(study.getDepartment().hashCode(),
                        (k) -> departmentStorage.find(study.getDepartment().getId()).orElse(study.getDepartment()));
                study.setDepartment(department);
            }

            if (study.getSpeciality() != null) {
                CSpeciality speciality = getSpeciality(study.getSpeciality(), specialityMap, specialityStorage);
                fillSpecialityStudy(speciality, study.getSpeciality().getStudy(), studyMap, studyStorage);
                study.setSpeciality(speciality);
            }

            p.setStudy(study);
        }
    }
}
