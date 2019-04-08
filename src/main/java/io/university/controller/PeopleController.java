package io.university.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.university.controller.aggregator.*;
import io.university.model.dao.CPerson;
import io.university.service.factory.impl.CPeopleFactory;
import io.university.storage.impl.CPersonStorage;
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

    @Autowired private CMongoController mongoController;
    @Autowired private CMySQLController mySQLController;
    @Autowired private COracleController oracleController;
    @Autowired private CPostgresController postgresController;

    @Autowired private CPersonStorage peopleStorage;

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
        oracleController.clean();
        postgresController.clean();
        mongoController.clean();
        mySQLController.clean();
        return true;
    }
}
