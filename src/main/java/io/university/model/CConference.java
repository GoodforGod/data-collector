package io.university.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.string.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 10.03.2019
 */
@Entity
public class CConference {

    @Id
    @NotNull
    @GenId
    private String id;

    @GenName
    private String name;

    @GenCountry
    private String country;

    @GenCity
    private String city;

    @GenCompany
    private String address;

    @GenTime
    private Timestamp timestamp;

    @JsonIgnore
    @ManyToMany(mappedBy = "conferences", cascade = CascadeType.ALL)
    private Set<CPerson> people = new HashSet<>();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public Set<CPerson> getPeople() {
        return people;
    }

    public CPerson addPerson(CPerson person) {
        this.people.add(person);
        return person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CConference that = (CConference) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
