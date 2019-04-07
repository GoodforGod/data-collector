package io.university.aggregator.model.dao.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenSet;
import io.dummymaker.annotation.simple.number.GenUInteger;
import io.dummymaker.annotation.simple.string.GenCity;
import io.dummymaker.annotation.simple.string.GenCountry;
import io.dummymaker.annotation.simple.string.GenId;
import io.dummymaker.generator.simple.impl.EmbeddedGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 11.03.2019
 */
@Entity
public class CCommunity implements Serializable {

    @Id
    @NotNull
    @GenId
    private String id;

    @GenUInteger
    private Integer roomCount;

    @GenUInteger
    private Integer payRentPerPerson;

    @GenCountry
    private String district;

    @GenCity
    private String street;

    @GenUInteger
    private Integer houseNumber;

    @GenUInteger
    private Integer housingNumber;

    @GenSet(value = EmbeddedGenerator.class, depth = 8, max = 4)
    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private Set<CRoom> rooms = new HashSet<>();

    @JsonIgnore
    @GenSet(value = EmbeddedGenerator.class, depth = 8, max = 4)
    @OneToMany(mappedBy = "community", cascade = CascadeType.ALL)
    private Set<CVisit> visits = new HashSet<>();

    public String getId() {
        return id;
    }

    public Integer getRoomCount() {
        return roomCount;
    }

    public Integer getPayRentPerPerson() {
        return payRentPerPerson;
    }

    public String getDistrict() {
        return district;
    }

    public String getStreet() {
        return street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public Integer getHousingNumber() {
        return housingNumber;
    }

    public Set<CRoom> getRooms() {
        return rooms;
    }

    public CRoom addRoom(CRoom room) {
        this.rooms.add(room);
        return room;
    }

    public Set<CVisit> getVisits() {
        return visits;
    }

    public CVisit addVisit(CVisit visit) {
        this.visits.add(visit);
        return visit;
    }

    public Set<CRoom> updateRooms(Collection<CRoom> rooms) {
        return (this.rooms = new HashSet<>(rooms));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CCommunity that = (CCommunity) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
