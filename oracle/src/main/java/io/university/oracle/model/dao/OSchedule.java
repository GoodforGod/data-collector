package io.university.oracle.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.number.GenUInteger;
import io.dummymaker.annotation.simple.number.GenUShort;
import io.dummymaker.annotation.simple.string.GenId;
import io.university.oracle.model.IUpdatable;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Entity
public class OSchedule implements IUpdatable<OSchedule>, Serializable {

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
    private Set<OPerson> people = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_uid")
    private OSubject subject;

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

    public Set<OPerson> getPeople() {
        return people;
    }

    public OPerson addPerson(OPerson person) {
        this.people.add(person);
        return person;
    }

    public OSubject getSubject() {
        return subject;
    }

    public void setSubject(OSubject subject) {
        this.subject = subject;
    }

    @Override
    public void update(OSchedule oSchedule) {
        this.audience = oSchedule.getAudience();
        this.campusId = oSchedule.getCampusId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OSchedule oSchedule = (OSchedule) o;

        return id != null ? id.equals(oSchedule.id) : oSchedule.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
