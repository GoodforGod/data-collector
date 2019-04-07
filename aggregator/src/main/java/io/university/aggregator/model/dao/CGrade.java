package io.university.aggregator.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.number.GenCharacter;
import io.dummymaker.annotation.simple.number.GenUByte;
import io.dummymaker.annotation.simple.string.GenId;
import io.university.aggregator.model.IUpdatable;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 05.03.2019
 */
@Entity
public class CGrade implements IUpdatable<CGrade>, Serializable {

    @Id
    @GenId
    private String id;

    @GenUByte
    private Integer numValue;

    @GenCharacter
    private Character latinValue;

    @GenTime
    private Timestamp gradeTimestamp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_uid")
    private CSubject subject;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_uid")
    private CPerson person;

    public String getId() {
        return id;
    }

    public Integer getNumValue() {
        return numValue;
    }

    public Character getLatinValue() {
        return latinValue;
    }

    public Timestamp getGradeTimestamp() {
        return gradeTimestamp;
    }

    public CSubject getSubject() {
        return subject;
    }

    public void setSubject(CSubject subject) {
        this.subject = subject;
    }

    public CPerson getPerson() {
        return person;
    }

    public void setPerson(CPerson person) {
        this.person = person;
    }

    @Override
    public void update(CGrade oGrade) {
        this.numValue = oGrade.getNumValue();
        this.latinValue = oGrade.getLatinValue();
        this.gradeTimestamp = oGrade.getGradeTimestamp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CGrade cGrade = (CGrade) o;

        return id != null ? id.equals(cGrade.id) : cGrade.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
