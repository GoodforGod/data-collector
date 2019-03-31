package io.university.aggregator.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.number.GenUInteger;
import io.dummymaker.annotation.simple.number.GenUShort;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 05.03.2019
 */
@Entity
public class CSchedule implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @GenTime
    private Timestamp startTimestamp;

    @GenTime
    private Timestamp endTimestamp;

    @GenUShort
    private String audience;

    @GenUInteger
    private Integer campusId;

    @JsonIgnore
    @ManyToMany(mappedBy = "schedules", cascade = CascadeType.ALL)
    private Set<CPerson> people = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_uid")
    private CSubject subject;

    public Integer getId() {
        return id;
    }

    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    public Timestamp getEndTimestamp() {
        return endTimestamp;
    }

    public String getAudience() {
        return audience;
    }

    public int getCampusId() {
        return campusId;
    }

    public Set<CPerson> getPeople() {
        return people;
    }

    public CPerson addPerson(CPerson person) {
        this.people.add(person);
        return person;
    }

    public CSubject getSubject() {
        return subject;
    }

    public void setSubject(CSubject subject) {
        this.subject = subject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CSchedule cSchedule = (CSchedule) o;

        if (startTimestamp != null ? !startTimestamp.equals(cSchedule.startTimestamp) : cSchedule.startTimestamp != null)
            return false;
        if (endTimestamp != null ? !endTimestamp.equals(cSchedule.endTimestamp) : cSchedule.endTimestamp != null)
            return false;
        if (audience != null ? !audience.equals(cSchedule.audience) : cSchedule.audience != null) return false;
        return campusId != null ? campusId.equals(cSchedule.campusId) : cSchedule.campusId == null;
    }

    @Override
    public int hashCode() {
        int result = startTimestamp != null ? startTimestamp.hashCode() : 0;
        result = 31 * result + (endTimestamp != null ? endTimestamp.hashCode() : 0);
        result = 31 * result + (audience != null ? audience.hashCode() : 0);
        result = 31 * result + (campusId != null ? campusId.hashCode() : 0);
        return result;
    }
}
