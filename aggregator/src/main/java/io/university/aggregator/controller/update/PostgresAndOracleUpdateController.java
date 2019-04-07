package io.university.aggregator.controller.update;

import io.university.aggregator.model.dao.*;
import io.university.aggregator.storage.impl.*;
import io.university.api.error.NotUpdatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 06.04.2019
 */
@RestController
@RequestMapping("/postgres_oracle/update")
public class PostgresAndOracleUpdateController {

    private static final Logger logger = LoggerFactory.getLogger(PostgresAndOracleUpdateController.class);

    @Autowired private CDepartmentStorage departmentStorage;
    @Autowired private CSpecialityStorage specialityStorage;
    @Autowired private CScheduleStorage scheduleStorage;
    @Autowired private CSubjectStorage subjectStorage;
    @Autowired private CPersonStorage peopleStorage;
    @Autowired private CStudyStorage studyStorage;
    @Autowired private CGradeStorage gradeStorage;
    @Autowired private CWorkStorage workStorage;

    @PostMapping("/department")
    public CDepartment postOrUpdateSpeciality(@RequestBody CDepartment department) {
        CDepartment modelToUpdate = departmentStorage.find(department.getId()).orElse(department);
        modelToUpdate.update(department);
        if (!departmentStorage.save(modelToUpdate).isPresent()) {
            logger.warn("Model not updated!");
            throw new NotUpdatedException();
        }

        return modelToUpdate;
    }

    @PostMapping("/schedule")
    public CSchedule postOrUpdateSubject(@RequestBody CSchedule schedule) {
        CSchedule modelToUpdate = scheduleStorage.find(schedule.getId()).orElse(schedule);
        modelToUpdate.update(schedule);
        if (!scheduleStorage.save(modelToUpdate).isPresent()) {
            logger.warn("Model not updated!");
            throw new NotUpdatedException();
        }

        return modelToUpdate;
    }


    @PostMapping("/work")
    public CWorkHistory postOrUpdateStudy(@RequestBody CWorkHistory workHistory) {
        CWorkHistory modelToUpdate = workStorage.find(workHistory.getId()).orElse(workHistory);
        modelToUpdate.update(workHistory);
        if (!workStorage.save(modelToUpdate).isPresent()) {
            logger.warn("Model not updated!");
            throw new NotUpdatedException();
        }

        return modelToUpdate;
    }




    @PostMapping("/grade")
    public CGrade postOrUpdateGrade(@RequestBody CGrade grade) {
        CGrade modelToUpdate = gradeStorage.find(grade.getId()).orElse(grade);
        modelToUpdate.update(grade);
        if (!gradeStorage.save(modelToUpdate).isPresent()) {
            logger.warn("Model not updated!");
            throw new NotUpdatedException();
        }

        return modelToUpdate;
    }

    @PostMapping("/speciality")
    public CSpeciality postOrUpdateSpeciality(@RequestBody CSpeciality speciality) {
        CSpeciality modelToUpdate = specialityStorage.find(speciality.getCode()).orElse(speciality);
        modelToUpdate.update(speciality);
        if (!specialityStorage.save(modelToUpdate).isPresent()) {
            logger.warn("Model not updated!");
            throw new NotUpdatedException();
        }

        return modelToUpdate;
    }

    @PostMapping("/subject")
    public CSubject postOrUpdateSubject(@RequestBody CSubject subject) {
        CSubject modelToUpdate = subjectStorage.find(subject.getCode()).orElse(subject);
        modelToUpdate.update(subject);
        if (!subjectStorage.save(modelToUpdate).isPresent()) {
            logger.warn("Model not updated!");
            throw new NotUpdatedException();
        }

        return modelToUpdate;
    }

    @PostMapping("/person")
    public CPerson postOrUpdatePerson(@RequestBody CPerson p) {
        CPerson modelToUpdate = peopleStorage.findByFullNameAndBirth(p.getName(), p.getMiddleName(), p.getSurname(), p.getBirthPlace(), p.getBirthTimestamp())
                .orElse(p);
        modelToUpdate.update(p);
        if (!peopleStorage.save(modelToUpdate).isPresent()) {
            logger.warn("Model not updated!");
            throw new NotUpdatedException();
        }

        return modelToUpdate;
    }

    @PostMapping("/study")
    public CStudy postOrUpdateStudy(@RequestBody CStudy study) {
        CStudy modelToUpdate = studyStorage.find(study.getId()).orElse(study);
        modelToUpdate.update(study);
        if (!studyStorage.save(modelToUpdate).isPresent()) {
            logger.warn("Model not updated!");
            throw new NotUpdatedException();
        }

        return modelToUpdate;
    }
}
