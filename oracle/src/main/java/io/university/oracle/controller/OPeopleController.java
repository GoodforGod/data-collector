package io.university.oracle.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.university.api.controller.BasicDatabaseController;
import io.university.oracle.model.dao.OPerson;
import io.university.oracle.service.factory.impl.OPeopleFactory;
import io.university.oracle.storage.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 01.03.2019
 */
@RestController
@RequestMapping("/oracle/people")
public class OPeopleController extends BasicDatabaseController<OPerson> {

    @Autowired private ODepartmentStorage departmentStorage;
    @Autowired private OSpecialityStorage specialityStorage;
    @Autowired private OScheduleStorage scheduleStorage;
    @Autowired private OSubjectStorage subjectStorage;
    @Autowired private OPersonStorage peopleStorage;
    @Autowired private OStudyStorage studyStorage;
    @Autowired private OWorkStorage workStorage;
    @Autowired private OGradeStorage gradeStorage;

    @Autowired
    public OPeopleController(OPeopleFactory factory) {
        super(factory);
    }

    @Override
    protected List<OPerson> filterOtherDatabases(List<OPerson> list) {
        return list;
    }

    @GetMapping("/all")
    public List<OPerson> getAll() {
        return peopleStorage.findAll();
    }

    @ApiOperation(value = "Generate Oracle schema")
    @GetMapping("/generate")
    public List<OPerson> generate(
            @ApiParam(value = "Amount users to generate", defaultValue = "2")
            @RequestParam(value = "amount", required = false) Integer amount
    ) {
        final int generateAmount = (amount == null || amount < 1) ? 1 : amount;
        return generateAsJson(generateAmount);
    }

    @GetMapping("/fill")
    public List<OPerson> fillWithPeople(
            @ApiParam(value = "Amount users to generate", defaultValue = "2")
            @RequestParam(value = "amount", required = false) Integer amount
    ) {
        final List<OPerson> people = generateValid(amount);
        peopleStorage.save(people);
        return transform(people);
    }

    @ApiOperation(value = "Clean up Oracle provider full data")
    @GetMapping("/clean")
    public Boolean clean() {
        final Set<Integer> peopleIds = workStorage.findAll().stream()
                .map(p -> p.getPerson().getId())
                .collect(Collectors.toSet());

        scheduleStorage.findAll().forEach(s -> peopleIds.addAll(
                s.getPeople().stream()
                        .map(OPerson::getId)
                        .collect(Collectors.toSet()))
        );

        departmentStorage.deleteAll();
        specialityStorage.deleteAll();
        scheduleStorage.deleteAll();
        subjectStorage.deleteAll();
        studyStorage.deleteAll();
        workStorage.deleteAll();
        gradeStorage.deleteAll();
        peopleIds.forEach(id -> peopleStorage.deleteById(id));
        return true;
    }
}
