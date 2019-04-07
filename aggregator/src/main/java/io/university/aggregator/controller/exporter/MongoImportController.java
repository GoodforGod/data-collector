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
@RequestMapping("/mongo/update")
public class MongoImportController {

    private static final Logger logger = LoggerFactory.getLogger(MongoImportController.class);

    @Autowired private CPersonStorage peopleStorage;
    @Autowired private CCommunityStorage communityStorage;
    @Autowired private CLivingStorage livingStorage;
    @Autowired private CRoomStorage roomStorage;
    @Autowired private CVisitStorage visitStorage;
}
