package io.university.postgres.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.university.api.controller.BasicDatabaseController;
import io.university.postgres.model.dao.Person;
import io.university.postgres.service.factory.impl.PeopleFactory;
import io.university.postgres.storage.impl.*;
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
@RequestMapping("/postgres/people")
public class PeopleController extends BasicDatabaseController<Person> {

    @Autowired private SpecialityStorage specialityStorage;
    @Autowired private SubjectStorage subjectStorage;
    @Autowired private PersonStorage peopleStorage;
    @Autowired private StudyStorage studyStorage;
    @Autowired private GradeStorage gradeStorage;

    @Autowired
    public PeopleController(PeopleFactory factory) {
        super(factory);
    }

    @Override
    protected List<Person> filterOtherDatabases(List<Person> list) {
        return list;
    }

    @GetMapping("/all")
    public List<Person> getAll() {
        return peopleStorage.findAll();
    }

    @GetMapping("/export/all")
    public List<Person> exportAll() {
        return peopleStorage.findAll();
    }

    @ApiOperation(value = "Generate Postgres people data")
    @GetMapping("/generate")
    public List<Person> generate(
            @ApiParam(value = "Amount users to generate", defaultValue = "2")
            @RequestParam(value = "amount", required = false) Integer amount
    ) {
        final int generateAmount = (amount == null || amount < 1) ? 1 : amount;
        return generateAsJson(generateAmount);
    }

    @GetMapping("/fill")
    public List<Person> fillWithPeople(
            @ApiParam(value = "Amount users to generate", defaultValue = "2")
            @RequestParam(value = "amount", required = false) Integer amount
    ) {
        final List<Person> people = generateValid(amount);
        peopleStorage.save(people);
        return transform(people);
    }

    @ApiOperation(value = "Clean up Postgres people data")
    @GetMapping("/clean")
    public Boolean clean() {
        final Set<Integer> peopleIds = studyStorage.findAll().stream()
                .map(p -> p.getPerson().getId())
                .collect(Collectors.toSet());

        specialityStorage.deleteAll();
        subjectStorage.deleteAll();
        studyStorage.deleteAll();
        gradeStorage.deleteAll();
        peopleIds.forEach(id -> peopleStorage.deleteById(id));
        return true;
    }
}
