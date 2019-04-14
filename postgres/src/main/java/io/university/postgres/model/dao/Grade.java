package io.university.postgres.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.number.GenUShort;
import io.dummymaker.annotation.simple.string.GenId;
import io.university.postgres.model.IUpdatable;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Entity
public class Grade implements IUpdatable<Grade>, Serializable {

    @Id
    @GenId
    private String id;

    @GenUShort
    private Integer numValue;

    @GenTime
    private Timestamp gradeTimestamp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_uid")
    private Subject subject;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_uid")
    private Person person;

    public String getId() {
        return id;
    }

    public Integer getNumValue() {
        return numValue;
    }

    public Timestamp getGradeTimestamp() {
        return gradeTimestamp;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void update(Grade grade) {
        this.numValue = grade.getNumValue();
        this.gradeTimestamp = grade.getGradeTimestamp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Grade grade = (Grade) o;

        return id != null ? id.equals(grade.id) : grade.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
