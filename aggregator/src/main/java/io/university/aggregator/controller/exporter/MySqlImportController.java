package io.university.aggregator.controller.exporter;

import io.university.aggregator.model.dao.CPerson;
import io.university.aggregator.model.dao.mysql.*;
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
@RequestMapping("/mysql/update")
public class MySqlImportController {

    private static final Logger logger = LoggerFactory.getLogger(MySqlImportController.class);

    @Autowired private CPublishmentStorage publishmentStorage;
    @Autowired private CConferenceStorage conferenceStorage;
    @Autowired private CEditionStorage editionStorage;
    @Autowired private CProjectStorage projectStorage;
    @Autowired private CPersonStorage peopleStorage;
    @Autowired private CBookStorage bookStorage;

    @PostMapping("/publishment")
    public CPublishment postOrUpdatePublishment(@RequestBody CPublishment model) {
        CPublishment modelToUpdate = publishmentStorage.find(model.getId()).orElse(model);
        modelToUpdate.update(model);
        if (!publishmentStorage.save(modelToUpdate).isPresent()) {
            logger.warn("Model not updated!");
            throw new NotUpdatedException();
        }

        return modelToUpdate;
    }

    @PostMapping("/conference")
    public CConference postOrUpdateConference(@RequestBody CConference model) {
        CConference modelToUpdate = conferenceStorage.find(model.getId()).orElse(model);
        modelToUpdate.update(model);
        if (!conferenceStorage.save(modelToUpdate).isPresent()) {
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

    @PostMapping("/edition")
    public CEdition postOrUpdateEdition(@RequestBody CEdition edition) {
        CEdition modelToUpdate = editionStorage.find(edition.getId()).orElse(edition);
        modelToUpdate.update(edition);
        if (!editionStorage.save(modelToUpdate).isPresent()) {
            logger.warn("Model not updated!");
            throw new NotUpdatedException();
        }

        return modelToUpdate;
    }

    @PostMapping("/project")
    public CProject postOrUpdateProject(@RequestBody CProject project) {
        CProject modelToUpdate = projectStorage.find(project.getId()).orElse(project);
        modelToUpdate.update(project);
        if (!projectStorage.save(modelToUpdate).isPresent()) {
            logger.warn("Model not updated!");
            throw new NotUpdatedException();
        }

        return modelToUpdate;
    }

    @PostMapping("/book")
    public CBook postOrUpdateRoom(@RequestBody CBook book) {
        CBook modelToUpdate = bookStorage.find(book.getIsbn()).orElse(book);
        modelToUpdate.update(book);
        if (!bookStorage.save(modelToUpdate).isPresent()) {
            logger.warn("Model not updated!");
            throw new NotUpdatedException();
        }

        return modelToUpdate;
    }
}
