package io.university.aggregator.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.university.aggregator.controller.aggregator.CMongoController;
import io.university.aggregator.controller.aggregator.CMySQLController;
import io.university.aggregator.controller.aggregator.COracleController;
import io.university.aggregator.controller.aggregator.CPostgresController;
import io.university.aggregator.model.dao.CPerson;
import io.university.aggregator.service.factory.impl.CPeopleFactory;
import io.university.aggregator.storage.impl.CPersonStorage;
import io.university.api.controller.BasicDatabaseController;
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
public class PeopleController extends BasicDatabaseController<CPerson> {

    @Autowired private CMongoController mongoController;
    @Autowired private CMySQLController mySQLController;
    @Autowired private COracleController oracleController;
    @Autowired private CPostgresController postgresController;

    @Autowired private CPersonStorage peopleStorage;

    @Autowired
    public PeopleController(CPeopleFactory factory) {
        super(factory, new TypeReference<List<CPerson>>() { });
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
