package io.university.service.validator.impl;

import io.university.model.dao.common.*;
import io.university.service.validator.IValidator;
import io.university.storage.impl.common.*;
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

        final Map<Integer, CBook> bookMap = new HashMap<>();
        final Map<Integer, CReading> readingMap = new HashMap<>();
        final Map<Integer, CProject> projectMap = new HashMap<>();
        final Map<Integer, CProjectParticipation> participationMap = new HashMap<>();
        final Map<Integer, CConference> conferenceMap = new HashMap<>();
        final Map<Integer, CEdition> editionMap = new HashMap<>();
        final Map<Integer, CPublishment> publishmentMap = new HashMap<>();

        final List<CPerson> validPeople = new ArrayList<>(people.size());

        for (CPerson p : people) {
            final CPerson validPerson = peopleStorage.findByFullNameAndBirth(
                    p.getName(),
                    p.getMiddleName(),
                    p.getSurname(),
                    p.getBirthPlace(),
                    p.getBirthTimestamp()).orElse(p);
            validPerson.setCitationIndex(p.getCitationIndex());

            if(!CollectionUtils.isEmpty(p.getReadings())) {
                final List<CReading> validReadings = new ArrayList<>(p.getReadings().size());
                for (CReading reading : p.getReadings()) {
                    if (reading.getBook() == null)
                        continue;
                    CBook book = bookMap.computeIfAbsent(reading.getBook().hashCode(),
                            (k) -> bookStorage.find(reading.getBook().getId()).orElse(reading.getBook()));
                    reading.setBook(book);
                    validReadings.add(reading);
                }
                validPerson.clearReadings();
                validReadings.forEach(validPerson::addReading);
            }

            if(!CollectionUtils.isEmpty(p.getConferences())) {
                final List<CConference> validConferences = new ArrayList<>(p.getConferences().size());
                for (CConference conference : p.getConferences()) {
                    CConference validConference = conferenceMap.computeIfAbsent(conference.hashCode(),
                            (k) -> conferenceStorage.find(conference.getId()).orElse(conference));
                    validConferences.add(validConference);
                }
                validPerson.clearConference();
                validConferences.forEach(validPerson::addConference);
            }

            if(!CollectionUtils.isEmpty(p.getParticipations())) {
                final List<CProjectParticipation> validParticipations = new ArrayList<>(p.getParticipations().size());
                for (CProjectParticipation participation : p.getParticipations()) {
                    if (participation.getProject() == null)
                        continue;
                    CProject project = projectMap.computeIfAbsent(participation.getProject().hashCode(),
                            (k) -> projectStorage.find(participation.getProject().getId()).orElse(participation.getProject()));
                    participation.setProject(project);
                    validParticipations.add(participation);
                }
                validPerson.clearParticipation();
                validParticipations.forEach(validPerson::addParticipation);
            }

            if(!CollectionUtils.isEmpty(p.getPublishments())) {
                final List<CPublishment> validPublishments = new ArrayList<>(p.getPublishments().size());
                for (CPublishment publishment : p.getPublishments()) {
                    if (publishment.getEdition() == null)
                        continue;

                    CEdition edition = editionMap.computeIfAbsent(publishment.getEdition().hashCode(),
                            (k) -> editionStorage.find(publishment.getEdition().getId()).orElse(publishment.getEdition()));
                    publishment.setEdition(edition);
                    validPublishments.add(publishment);
                }

                if (!validPublishments.isEmpty()) {
                    validPerson.clearPublishment();
                    validPublishments.forEach(validPerson::addPublishment);
                }
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
