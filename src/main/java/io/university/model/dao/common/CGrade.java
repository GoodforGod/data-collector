package io.university.model.dao.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.number.GenCharacter;
import io.dummymaker.annotation.simple.number.GenUInteger;

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
public class CGrade implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @GenUInteger
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

    public Integer getId() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CGrade cGrade = (CGrade) o;

        if (id != null ? !id.equals(cGrade.id) : cGrade.id != null) return false;
        if (numValue != null ? !numValue.equals(cGrade.numValue) : cGrade.numValue != null) return false;
        if (latinValue != null ? !latinValue.equals(cGrade.latinValue) : cGrade.latinValue != null) return false;
        return gradeTimestamp != null ? gradeTimestamp.equals(cGrade.gradeTimestamp) : cGrade.gradeTimestamp == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numValue != null ? numValue.hashCode() : 0);
        result = 31 * result + (latinValue != null ? latinValue.hashCode() : 0);
        result = 31 * result + (gradeTimestamp != null ? gradeTimestamp.hashCode() : 0);
        return result;
    }
}
