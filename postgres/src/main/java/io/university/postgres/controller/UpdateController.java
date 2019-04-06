package io.university.postgres.controller;

import io.university.postgres.model.dao.Speciality;
import io.university.postgres.storage.impl.PersonStorage;
import io.university.postgres.storage.impl.SpecialityStorage;
import io.university.postgres.storage.impl.StudyStorage;
import io.university.postgres.storage.impl.SubjectStorage;
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
@RequestMapping("/postgres/update")
public class UpdateController {

    private static final Logger logger = LoggerFactory.getLogger(UpdateController.class);

    @Autowired
    private SpecialityStorage specialityStorage;
    @Autowired
    private SubjectStorage subjectStorage;
    @Autowired
    private PersonStorage personStorage;
    @Autowired
    private StudyStorage studyStorage;

    @PostMapping("/speciality")
    public Boolean postOrUpdateSpeciality(
            @RequestBody Speciality speciality
    ) {
        if (speciality == null) {
            logger.warn("Speciality is nullable");
            return false;
        }

        Speciality specialityToUpdate = specialityStorage.find(speciality.getCode()).orElse(speciality);
        specialityToUpdate.update(speciality);
        return specialityStorage.save(specialityToUpdate).isPresent();
    }

    public Boolean postOrUpdateSubject() {
        return false;
    }
}
