package io.university.aggregator.controller.exporter;

import io.university.aggregator.storage.impl.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 07.04.2019
 */
@RestController
@RequestMapping("/mysql/update")
public class MySqlImportController {

    private static final Logger logger = LoggerFactory.getLogger(MySqlImportController.class);

    @Autowired private CProjectParticipationStorage projectParticipationStorage;
    @Autowired private CPublishmentStorage publishmentStorage;
    @Autowired private CConferenceStorage conferenceStorage;
    @Autowired private CEditionStorage editionStorage;
    @Autowired private CProjectStorage projectStorage;
    @Autowired private CReadingStorage readingStorage;
    @Autowired private CPersonStorage peopleStorage;
    @Autowired private CBookStorage bookStorage;
}
