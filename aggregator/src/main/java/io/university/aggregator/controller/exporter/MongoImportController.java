package io.university.aggregator.controller.exporter;

import io.university.aggregator.model.dao.CPerson;
import io.university.aggregator.model.dao.mongo.CCommunity;
import io.university.aggregator.model.dao.mongo.CLiving;
import io.university.aggregator.model.dao.mongo.CRoom;
import io.university.aggregator.model.dao.mongo.CVisit;
import io.university.aggregator.storage.impl.*;
import io.university.api.error.NotUpdatedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @Autowired private CCommunityStorage communityStorage;
    @Autowired private CLivingStorage livingStorage;
    @Autowired private CPersonStorage peopleStorage;
    @Autowired private CVisitStorage visitStorage;
    @Autowired private CRoomStorage roomStorage;

    @PostMapping("/community")
    public CCommunity postOrUpdateCommunity(@RequestBody CCommunity community) {
        CCommunity modelToUpdate = communityStorage.find(community.getId()).orElse(community);
        modelToUpdate.update(community);
        if (!communityStorage.save(modelToUpdate).isPresent()) {
            logger.warn("Model not updated!");
            throw new NotUpdatedException();
        }

        return modelToUpdate;
    }

    @PostMapping("/living")
    public CLiving postOrUpdateLiving(@RequestBody CLiving living) {
        CLiving modelToUpdate = livingStorage.find(living.getId()).orElse(living);
        modelToUpdate.update(living);
        if (!livingStorage.save(modelToUpdate).isPresent()) {
            logger.warn("Model not updated!");
            throw new NotUpdatedException();
        }

        return modelToUpdate;
    }

    @PostMapping("/person")
    public CPerson postOrUpdatePerson(@RequestBody CPerson p) {
        CPerson modelToUpdate = peopleStorage.findByFullNameAndBirth(p.getName(), p.getMiddleName(), p.getSurname(), p.getBirthPlace(), p.getBirthTimestamp())
                .orElse(p);
        modelToUpdate.update(p);
        if (!peopleStorage.save(modelToUpdate).isPresent()) {
            logger.warn("Model not updated!");
            throw new NotUpdatedException();
        }

        return modelToUpdate;
    }

    @PostMapping("/visit")
    public CVisit postOrUpdateVisit(@RequestBody CVisit visit) {
        CVisit modelToUpdate = visitStorage.find(visit.getId()).orElse(visit);
        modelToUpdate.update(visit);
        if (!visitStorage.save(modelToUpdate).isPresent()) {
            logger.warn("Model not updated!");
            throw new NotUpdatedException();
        }

        return modelToUpdate;
    }

    @PostMapping("/room")
    public CRoom postOrUpdateRoom(@RequestBody CRoom rooom) {
        CRoom modelToUpdate = roomStorage.find(rooom.getId()).orElse(rooom);
        modelToUpdate.update(rooom);
        if (!roomStorage.save(modelToUpdate).isPresent()) {
            logger.warn("Model not updated!");
            throw new NotUpdatedException();
        }

        return modelToUpdate;
    }
}
