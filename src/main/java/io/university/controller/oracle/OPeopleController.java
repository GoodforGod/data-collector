package io.university.controller.oracle;

import io.swagger.annotations.ApiParam;
import io.university.model.dao.oracle.OPerson;
import io.university.service.factory.impl.OPeopleFactory;
import io.university.storage.impl.oracle.OPersonStorage;
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
 * @since 01.03.2019
 */
@RestController
@RequestMapping("/oracle/people")
public class OPeopleController {

    @Autowired private OPersonStorage peopleStorage;
    @Autowired private OPeopleFactory factory;

    @GetMapping("/all")
    public List<OPerson> getAll() {
        return peopleStorage.findAll();
    }

    @GetMapping("/fill")
    public List<OPerson> fillWithPeople(
            @ApiParam(value = "Amount users to generate", defaultValue = "2")
            @RequestParam(value = "amount", required = false) Integer amount
    ) {
        final int genAmount = (amount != null && amount > 0)
                ? amount
                : ThreadLocalRandom.current().nextInt(2, 4);

        final List<OPerson> people = factory.build(genAmount);
        peopleStorage.save(people);
        return people;
    }
}
