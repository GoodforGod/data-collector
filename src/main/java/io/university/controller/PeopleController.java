package io.university.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.university.factory.impl.CPeopleFactory;
import io.university.model.CPerson;
import io.university.storage.impl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 10.03.2019
 */
@RestController
@RequestMapping("/common/people")
public class PeopleController extends BasicDatabaseController {

    @Autowired private CCommunityStorage communityStorage;
    @Autowired private CPersonStorage peopleStorage;
    @Autowired private CLivingStorage livingStorage;
    @Autowired private CVisitStorage visitStorage;
    @Autowired private CRoomStorage roomStorage;
    @Autowired private CProjectParticipationStorage participationStorage;
    @Autowired private CPublishmentStorage publishmentStorage;
    @Autowired private CConferenceStorage conferenceStorage;
    @Autowired private CProjectStorage projectStorage;
    @Autowired private CEditionStorage editionStorage;
    @Autowired private CReadingStorage readingStorage;
    @Autowired private CBookStorage bookStorage;
    @Autowired private CDepartmentStorage departmentStorage;
    @Autowired private CSpecialityStorage specialityStorage;
    @Autowired private CScheduleStorage scheduleStorage;
    @Autowired private CSubjectStorage subjectStorage;
    @Autowired private CStudyStorage studyStorage;
    @Autowired private CWorkStorage workStorage;
    @Autowired private CGradeStorage gradeStorage;

    @Autowired
    public PeopleController(CPeopleFactory factory) {
        super(factory);
    }

    @Override
    protected List<CPerson> filterOtherDatabases(List<CPerson> list) {
        return list;
    }

    @GetMapping("/all")
    public List<CPerson> getAll() {
        return peopleStorage.findAll();
    }

    @ApiOperation(value = "Generate Aggregator people")
    @GetMapping("/generate")
    public List<CPerson> generate(
            @ApiParam(value = "Amount users to generate", defaultValue = "2")
            @RequestParam(value = "amount", required = false) Integer amount
    ) {
        final int generateAmount = (amount == null || amount < 1) ? 1 : amount;
        return generateAsJson(generateAmount);
    }

    @GetMapping("/fill")
    public List<CPerson> fillWithPeople(
            @ApiParam(value = "Amount users to generate", defaultValue = "2")
            @RequestParam(value = "amount", required = false) Integer amount
    ) {
        final int genAmount = (amount != null && amount > 0) ? amount : 2;
        final List<CPerson> people = generateValid(genAmount);
        peopleStorage.save(people);
        return people;
    }

    @ApiOperation(value = "Clean up all data")
    @GetMapping("/clean")
    public Boolean clean() {
        visitStorage.deleteAll();
        livingStorage.deleteAll();
        roomStorage.deleteAll();
        communityStorage.deleteAll();
        participationStorage.deleteAll();
        publishmentStorage.deleteAll();
        conferenceStorage.deleteAll();
        projectStorage.deleteAll();
        editionStorage.deleteAll();
        readingStorage.deleteAll();
        bookStorage.deleteAll();
        departmentStorage.deleteAll();
        specialityStorage.deleteAll();
        scheduleStorage.deleteAll();
        subjectStorage.deleteAll();
        studyStorage.deleteAll();
        workStorage.deleteAll();
        gradeStorage.deleteAll();
        peopleStorage.deleteAll();
        return true;
    }
}
