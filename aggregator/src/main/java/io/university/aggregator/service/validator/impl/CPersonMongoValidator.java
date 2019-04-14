package io.university.aggregator.service.validator.impl;

import io.university.aggregator.model.dao.CPerson;
import io.university.aggregator.model.dao.mongo.CCommunity;
import io.university.aggregator.model.dao.mongo.CLiving;
import io.university.aggregator.model.dao.mongo.CRoom;
import io.university.aggregator.model.dao.mongo.CVisit;
import io.university.aggregator.storage.impl.*;
import io.university.api.service.validator.IValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 13.03.2019
 */
@Service
public class CPersonMongoValidator implements IValidator<CPerson> {

    @Autowired private CCommunityStorage communityStorage;
    @Autowired private CPersonStorage peopleStorage;
    @Autowired private CLivingStorage livingStorage;
    @Autowired private CVisitStorage visitStorage;
    @Autowired private CRoomStorage roomStorage;

    @Override
    public List<CPerson> validate(List<CPerson> people) {
        if (CollectionUtils.isEmpty(people))
            return Collections.emptyList();

        final Map<Integer, CCommunity> communityMap = new HashMap<>();
        final Map<Integer, CLiving> livingMap = new HashMap<>();
        final Map<Integer, CVisit> visitMap = new HashMap<>();
        final Map<Integer, CRoom> roomMap = new HashMap<>();

        final List<CPerson> validPeople = new ArrayList<>(people.size());

        for (CPerson p : people) {
            final CPerson validPerson = peopleStorage.findByFullNameAndBirth(
                    p.getName(),
                    p.getMiddleName(),
                    p.getSurname(),
                    p.getBirthPlace(),
                    p.getBirthTimestamp()).orElse(p);

            if (!CollectionUtils.isEmpty(p.getVisits())) {

                // May be should locate exist visits first, may be not
//                final Set<CVisit> visits = p.getVisits().stream()
//                        .map(v -> visitMap.computeIfAbsent(v.hashCode(),
//                                (k) -> visitStorage.find(v.getId()).orElse(v)))
//                        .collect(Collectors.toSet());

                for (CVisit visit : p.getVisits()) {
                    visit.setPerson(p);
                    if (visit.getCommunity() != null) {
                        CCommunity community = communityMap.computeIfAbsent(visit.getCommunity().hashCode(),
                                (k) -> communityStorage.find(visit.getCommunity().getId()).orElse(visit.getCommunity()));

                        visit.setCommunity(community);

                        if (!CollectionUtils.isEmpty(community.getRooms())) {

                            // Key as json room, value as valid DB room
                            final Map<CRoom, CRoom> roomPairMap = community.getRooms().stream()
                                    .collect(Collectors.toMap(r -> r, r -> roomMap.computeIfAbsent(r.hashCode(),
                                            (k) -> roomStorage.find(r.getId()).orElse(r))));

                            roomPairMap.forEach((k, v) -> {
                                    v.setCommunity(community);
                                    if(!CollectionUtils.isEmpty(k.getLivings())) {
                                        k.getLivings().forEach(l -> {
                                            CLiving living = livingMap.computeIfAbsent(l.hashCode(),
                                                    (lKey) -> livingStorage.find(l.getId()).orElse(l));
                                            living.setRoom(v);
                                            living.setPerson(p);
                                        });
                                    }
                            });

                            final Set<CRoom> rooms = roomPairMap.entrySet().stream()
                                    .map(Map.Entry::getValue)
                                    .collect(Collectors.toSet());

                            community.updateRooms(rooms);
                        }
                    }
                }
            }

            validPeople.add(validPerson);
        }

        return validPeople;
    }
}
