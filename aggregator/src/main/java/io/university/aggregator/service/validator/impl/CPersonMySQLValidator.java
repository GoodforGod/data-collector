package io.university.aggregator.service.validator.impl;

import io.university.aggregator.model.dao.CPerson;
import io.university.aggregator.model.dao.mysql.*;
import io.university.aggregator.storage.impl.*;
import io.university.api.service.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 13.03.2019
 */
@Service
public class CPersonMySQLValidator implements IValidator<CPerson> {

    @Autowired private CProjectParticipationStorage participationStorage;
    @Autowired private CPublishmentStorage publishmentStorage;
    @Autowired private CConferenceStorage conferenceStorage;
    @Autowired private CProjectStorage projectStorage;
    @Autowired private CEditionStorage editionStorage;
    @Autowired private CReadingStorage readingStorage;
    @Autowired private CPersonStorage peopleStorage;
    @Autowired private CBookStorage bookStorage;

    @Override
    public List<CPerson> validate(List<CPerson> people) {
        if (CollectionUtils.isEmpty(people))
            return Collections.emptyList();

        final Map<Integer, CProjectParticipation> participationMap = new HashMap<>();
        final Map<Integer, CPublishment> publishmentMap = new HashMap<>();
        final Map<Integer, CConference> conferenceMap = new HashMap<>();
        final Map<Integer, CEdition> editionMap = new HashMap<>();
        final Map<Integer, CReading> readingMap = new HashMap<>();
        final Map<Integer, CProject> projectMap = new HashMap<>();
        final Map<Integer, CBook> bookMap = new HashMap<>();

        final List<CPerson> validPeople = new ArrayList<>(people.size());

        for (CPerson p : people) {
            final CPerson validPerson = peopleStorage.findByFullNameAndBirth(
                    p.getName(),
                    p.getMiddleName(),
                    p.getSurname(),
                    p.getBirthPlace(),
                    p.getBirthTimestamp()).orElse(p);
            validPerson.setCitationIndex(p.getCitationIndex());

            if (!CollectionUtils.isEmpty(p.getReadings())) {
                final Set<CReading> validReadings = new HashSet<>(p.getReadings().size());
                for (CReading reading : p.getReadings()) {
                    if (reading.getBook() == null) {
                        validReadings.add(reading);
                        continue;
                    }

                    CBook book = bookMap.computeIfAbsent(reading.getBook().hashCode(),
                            (k) -> bookStorage.find(reading.getBook().getIsbn()).orElse(reading.getBook()));
                    reading.setBook(book);
                    reading.setPerson(validPerson);
                    validReadings.add(reading);
                }

                validPerson.getReadings().removeAll(validReadings);
                validPerson.getReadings().addAll(validReadings);
            }

            if (!CollectionUtils.isEmpty(p.getConferences())) {
                final Set<CConference> validConferences = new HashSet<>(p.getConferences().size());
                for (CConference conference : p.getConferences()) {
                    CConference validConference = conferenceMap.computeIfAbsent(conference.hashCode(),
                            (k) -> conferenceStorage.find(conference.getId()).orElse(conference));
                    validConferences.add(validConference);
                }

                validPerson.getConferences().removeAll(validConferences);
                validPerson.getConferences().addAll(validConferences);
            }

            if (!CollectionUtils.isEmpty(p.getParticipations())) {
                final Set<CProjectParticipation> validParticipations = new HashSet<>(p.getParticipations().size());
                for (CProjectParticipation participation : p.getParticipations()) {
                    if (participation.getProject() == null) {
                        validParticipations.add(participation);
                        continue;
                    }

                    CProject project = projectMap.computeIfAbsent(participation.getProject().hashCode(),
                            (k) -> projectStorage.find(participation.getProject().getId()).orElse(participation.getProject()));
                    participation.setProject(project);
                    participation.setPerson(validPerson);
                    validParticipations.add(participation);
                }

                validPerson.getParticipations().removeAll(validParticipations);
                validPerson.getParticipations().addAll(validParticipations);
            }

            if (!CollectionUtils.isEmpty(p.getPublishments())) {
                final Set<CPublishment> validPublishments = new HashSet<>(p.getPublishments().size());
                for (CPublishment publishment : p.getPublishments()) {
                    if (publishment.getEdition() == null) {
                        validPublishments.add(publishment);
                        continue;
                    }

                    CEdition edition = editionMap.computeIfAbsent(publishment.getEdition().hashCode(),
                            (k) -> editionStorage.find(publishment.getEdition().getId()).orElse(publishment.getEdition()));
                    publishment.setEdition(edition);
                    validPublishments.add(publishment);
                }

                validPerson.getPublishments().removeAll(validPublishments);
                validPerson.getPublishments().addAll(validPublishments);
            }

            validPerson.clearLivings();
            validPerson.clearVisits();
            validPerson.clearSchedule();
            validPerson.clearGrades();

            validPeople.add(validPerson);
        }

        return validPeople;
    }
}
