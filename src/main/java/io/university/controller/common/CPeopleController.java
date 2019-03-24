package io.university.controller.common;

import io.swagger.annotations.ApiParam;
import io.university.model.dao.common.CPerson;
import io.university.service.factory.impl.CPeopleFactory;
import io.university.storage.impl.common.CPersonStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 10.03.2019
 */
@RestController
@RequestMapping("/common/people")
public class CPeopleController {

    @Autowired private CPeopleFactory factory;
    @Autowired private CPersonStorage peopleStorage;

    @GetMapping("/all")
    public List<CPerson> getAll() {
        return peopleStorage.findAll();
    }

    @GetMapping("/fill")
    public List<CPerson> fillWithPeople(
            @ApiParam(value = "Amount users to generate", defaultValue = "2")
            @RequestParam(value = "amount", required = false) Integer amount
    ) {
        final int genAmount = (amount != null && amount > 0)
                ? amount
                : ThreadLocalRandom.current().nextInt(2, 4);

        final List<CPerson> people = factory.build(genAmount);
        peopleStorage.save(people);
        return people;
    }
}
