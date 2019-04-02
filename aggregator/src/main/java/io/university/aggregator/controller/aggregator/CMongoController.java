package io.university.aggregator.controller.aggregator;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.university.aggregator.dao.CPerson;
import io.university.aggregator.service.factory.impl.CPeopleFactory;
import io.university.aggregator.service.validator.impl.CPersonMongoValidator;
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
@RequestMapping("/common/mongo")
public class CMongoController extends BasicDatabaseController<CPerson> {

    @Autowired private CCommunityStorage communityStorage;
    @Autowired private CPersonStorage peopleStorage;
    @Autowired private CLivingStorage livingStorage;
    @Autowired private CVisitStorage visitStorage;
    @Autowired private CRoomStorage roomStorage;

    @Autowired private CPersonMongoValidator validator;

    @Autowired
    public CMongoController(CPeopleFactory factory) {
        super(factory);
    }

    @Override
    protected List<CPerson> filterOtherDatabases(final List<CPerson> list) {
        if (CollectionUtils.isEmpty(list))
            return Collections.emptyList();

        return list.stream().peek(p -> {
            p.setStudy(null);
            p.setWorkHistory(null);
            p.clearConference();
            p.clearGrades();
            p.clearParticipation();
            p.clearPublishment();
            p.clearReadings();
            p.clearSchedule();
        }).collect(Collectors.toList());
    }

    @ApiOperation(
            value = "Generate MongoDB schema",
            notes = "Generate MongoDB people data as schema describe"
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
            value = "Clean up MongoDB schema",
            notes = "Clean up MongoDB people full data"
    )
    @GetMapping("/clean")
    public Boolean clean() {
        final Set<Integer> peopleIds = visitStorage.findAll().stream()
                .map(v -> v.getPerson().getId())
                .collect(Collectors.toSet());

        livingStorage.findAll().forEach(v -> peopleIds.add(v.getPerson().getId()));

        visitStorage.deleteAll();
        livingStorage.deleteAll();
        roomStorage.deleteAll();
        communityStorage.deleteAll();
        peopleIds.forEach(id -> peopleStorage.deleteById(id));
        return true;
    }

    @ApiOperation(
            value = "Load emulation MongoDB",
            notes = "Emulates load operation for MongoDB"
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
            value = "Load endpoint for MongoDB",
            notes = "Load endpoint to post data for MongoDB"
    )
    @PostMapping("/load")
    public List<CPerson> load(@RequestBody final List<CPerson> people) {
        final List<CPerson> validated = validator.validate(people);
        return peopleStorage.save(validated);
    }
}
