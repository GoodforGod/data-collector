package io.university.model.dao;

import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.number.GenShort;
import io.dummymaker.annotation.simple.number.GenUInteger;

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
@Table(schema = "sys")
public class Schedule implements Serializable {

    @Id
    @GeneratedValue
    private int id;

    @GenTime
    private Timestamp startTimestamp;
    @GenTime
    private Timestamp endTimestamp;
    @GenShort
    private String audience;
    @GenUInteger
    private Integer campusId;

    @ManyToMany(mappedBy = "schedules", cascade = CascadeType.ALL)
    private Set<Person> people = new HashSet<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_uid")
    private Subject subject;

    public int getId() {
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

    public Set<Person> getPeople() {
        return people;
    }

    public Person addPerson(Person person) {
        this.people.add(person);
        return person;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
        subject.setSchedule(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Schedule schedule = (Schedule) o;

        if (id != schedule.id) return false;
        if (startTimestamp != null ? !startTimestamp.equals(schedule.startTimestamp) : schedule.startTimestamp != null)
            return false;
        if (endTimestamp != null ? !endTimestamp.equals(schedule.endTimestamp) : schedule.endTimestamp != null)
            return false;
        if (audience != null ? !audience.equals(schedule.audience) : schedule.audience != null) return false;
        if (campusId != null ? !campusId.equals(schedule.campusId) : schedule.campusId != null) return false;
        return subject != null ? subject.equals(schedule.subject) : schedule.subject == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (startTimestamp != null ? startTimestamp.hashCode() : 0);
        result = 31 * result + (endTimestamp != null ? endTimestamp.hashCode() : 0);
        result = 31 * result + (audience != null ? audience.hashCode() : 0);
        result = 31 * result + (campusId != null ? campusId.hashCode() : 0);
        result = 31 * result + (subject != null ? subject.hashCode() : 0);
        return result;
    }
}
