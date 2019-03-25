package io.university.model.dao.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenSet;
import io.dummymaker.annotation.simple.number.GenUInteger;
import io.dummymaker.annotation.simple.string.GenCity;
import io.dummymaker.annotation.simple.string.GenCountry;
import io.dummymaker.generator.simple.impl.EmbeddedGenerator;

import javax.persistence.*;
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
    @GeneratedValue
    private Integer id;

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

    public Integer getId() {
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

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (roomCount != null ? !roomCount.equals(that.roomCount) : that.roomCount != null) return false;
        if (payRentPerPerson != null ? !payRentPerPerson.equals(that.payRentPerPerson) : that.payRentPerPerson != null)
            return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (houseNumber != null ? !houseNumber.equals(that.houseNumber) : that.houseNumber != null) return false;
        return housingNumber != null ? housingNumber.equals(that.housingNumber) : that.housingNumber == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (roomCount != null ? roomCount.hashCode() : 0);
        result = 31 * result + (payRentPerPerson != null ? payRentPerPerson.hashCode() : 0);
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (houseNumber != null ? houseNumber.hashCode() : 0);
        result = 31 * result + (housingNumber != null ? housingNumber.hashCode() : 0);
        return result;
    }
}
