package io.university.aggregator.controller.aggregator;

import com.fasterxml.jackson.core.type.TypeReference;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.university.aggregator.model.dao.CPerson;
import io.university.aggregator.service.factory.impl.CPeopleFactory;
import io.university.aggregator.service.validator.impl.CPersonOracleValidator;
import io.university.aggregator.storage.impl.*;
import io.university.api.controller.BasicDatabaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 13.03.2019
 */
@RestController
@RequestMapping("/common/oracle")
public class COracleController extends BasicDatabaseController<CPerson> {

    @Autowired private CDepartmentStorage departmentStorage;
    @Autowired private CSpecialityStorage specialityStorage;
    @Autowired private CScheduleStorage scheduleStorage;
    @Autowired private CSubjectStorage subjectStorage;
    @Autowired private CPersonStorage peopleStorage;
    @Autowired private CStudyStorage studyStorage;
    @Autowired private CWorkStorage workStorage;
    @Autowired private CGradeStorage gradeStorage;

    @Autowired private CPersonOracleValidator validator;

    @Autowired
    public COracleController(CPeopleFactory factory) {
        super(factory, new TypeReference<List<CPerson>>() { });
    }

    @Override
    protected List<CPerson> filterOtherDatabases(final List<CPerson> list) {
        if (CollectionUtils.isEmpty(list))
            return Collections.emptyList();

        return list.stream().peek(p -> {
            p.clearConference();
            p.clearLivings();
            p.clearParticipation();
            p.clearPublishment();
            p.clearReadings();
            p.clearVisits();
        }).collect(Collectors.toList());
    }

    @ApiOperation(value = "Clean up Oracle people data")
    @GetMapping("/clean")
    public Boolean clean() {
        final Set<Integer> peopleIds = workStorage.findAll().stream()
                .map(p -> p.getPerson().getId())
                .collect(Collectors.toSet());

        scheduleStorage.findAll().forEach(s -> peopleIds.addAll(
                s.getPeople().stream()
                        .map(CPerson::getId)
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

    @ApiOperation(value = "Generate Oracle people data")
    @GetMapping("/generate")
    public List<CPerson> generate(
            @ApiParam(value = "Amount users to generate", defaultValue = "2")
            @RequestParam(value = "amount", required = false) Integer amount
    ) {
        final int generateAmount = (amount == null || amount < 1) ? 1 : amount;
        return generateAsJson(generateAmount);
    }

    @ApiOperation(value = "Emulates load operation for Oracle")
    @GetMapping("/load/test")
    public List<CPerson> testLoad(
            @ApiParam(value = "Amount users to generate", defaultValue = "2")
            @RequestParam(value = "amount", required = false) Integer amount
    ) {
        final int generateAmount = (amount == null || amount < 1) ? ThreadLocalRandom.current().nextInt(2, 4) : amount;
        final List<CPerson> people = generateAsJson(generateAmount);
        return load(people);
    }

    @ApiOperation(value = "Load endpoint for Oracle")
    @PostMapping("/load")
    public List<CPerson> load(@RequestBody final List<CPerson> people) {
        final List<CPerson> validated = validator.validate(people);
        return peopleStorage.save(validated);
    }
}
