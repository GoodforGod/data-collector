package io.university.controller.common.aggregator;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.university.model.dao.common.CPerson;
import io.university.service.factory.impl.CPeopleFactory;
import io.university.service.validator.impl.CPersonMySQLValidator;
import io.university.storage.impl.common.*;
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
@RequestMapping("/common/mysql")
public class CMySQLController extends BasicDatabaseController {

    @Autowired private CProjectParticipationStorage participationStorage;
    @Autowired private CPublishmentStorage publishmentStorage;
    @Autowired private CConferenceStorage conferenceStorage;
    @Autowired private CProjectStorage projectStorage;
    @Autowired private CEditionStorage editionStorage;
    @Autowired private CReadingStorage readingStorage;
    @Autowired private CPersonStorage peopleStorage;
    @Autowired private CBookStorage bookStorage;

    @Autowired private CPersonMySQLValidator validator;

    @Autowired
    public CMySQLController(CPeopleFactory factory) {
        super(factory);
    }

    @Override
    List<CPerson> filterOtherDatabases(final List<CPerson> list) {
        if (CollectionUtils.isEmpty(list))
            return Collections.emptyList();

        return list.stream().peek(p -> {
            p.setStudy(null);
            p.setWorkHistory(null);
            p.clearGrades();
            p.clearLivings();
            p.clearSchedule();
            p.clearVisits();
        }).collect(Collectors.toList());
    }

    @ApiOperation(
            value = "Clean up MySQL schema",
            notes = "Clean up MySQL people full data"
    )
    @GetMapping("/clean")
    public Boolean clean() {
        final Set<Integer> peopleIds = participationStorage.findAll().stream()
                .map(p -> p.getPerson().getId())
                .collect(Collectors.toSet());

        readingStorage.findAll().forEach(r -> peopleIds.add(r.getPerson().getId()));
        publishmentStorage.findAll().forEach(p -> peopleIds.add(p.getPerson().getId()));

        participationStorage.deleteAll();
        publishmentStorage.deleteAll();
        conferenceStorage.deleteAll();
        projectStorage.deleteAll();
        editionStorage.deleteAll();
        readingStorage.deleteAll();
        bookStorage.deleteAll();
        peopleIds.forEach(id -> peopleStorage.deleteById(id));
        return true;
    }

    @ApiOperation(
            value = "Generate MySQL schema",
            notes = "Generate MySQL people data as schema describe"
    )
    @GetMapping("/generate")
    public List<CPerson> generate(
            @ApiParam(value = "Amount users to generate", defaultValue = "2")
            @RequestParam(value = "amount", required = false) Integer amount
    ) {
        final int generateAmount = (amount == null || amount < 1) ? 1 : amount;
        return generateAsJson(generateAmount);
    }

    @ApiOperation(
            value = "Load emulation MySQL",
            notes = "Emulates load operation for MySQL"
    )
    @GetMapping("/load/test")
    public List<CPerson> testLoad(
            @ApiParam(value = "Amount users to generate", defaultValue = "2")
            @RequestParam(value = "amount", required = false) Integer amount
    ) {
        final int generateAmount = (amount == null || amount < 1) ? ThreadLocalRandom.current().nextInt(2, 4) : amount;
        final List<CPerson> people = generateAsJson(generateAmount);
        return load(people);
    }

    @ApiOperation(
            value = "Load endpoint for MySQL",
            notes = "Load endpoint to post data for MySQL"
    )
    @PostMapping("/load")
    public List<CPerson> load(@RequestBody final List<CPerson> people) {
        final List<CPerson> validated = validator.validate(people);
        return peopleStorage.save(validated);
    }
}
