package io.university.aggregator.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.number.GenUInteger;
import io.dummymaker.annotation.simple.number.GenUShort;
import io.dummymaker.annotation.simple.string.GenId;
import io.university.api.model.IUpdatable;

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
public class CSchedule implements IUpdatable<CSchedule>, Serializable {

    @Id
    @GenId
    private String id;

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

    public String getId() {
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
    public void update(CSchedule oSchedule) {
        this.audience = oSchedule.getAudience();
        this.campusId = oSchedule.getCampusId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CSchedule cSchedule = (CSchedule) o;

        return id != null ? id.equals(cSchedule.id) : cSchedule.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
