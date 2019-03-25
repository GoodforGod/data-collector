package io.university.model.dao.oracle;

import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.number.GenCharacter;
import io.dummymaker.annotation.simple.number.GenUShort;
import io.dummymaker.annotation.simple.string.GenId;

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
public class OGrade implements Serializable {

    @Id
    @GenId
    private String id;

    @GenUShort
    private Integer numValue;

    @GenCharacter
    private Character latinValue;

    @GenTime
    private Timestamp gradeTimestamp;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subject_uid")
    private OSubject subject;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_uid")
    private OPerson person;

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

    public OSubject getSubject() {
        return subject;
    }

    public void setSubject(OSubject subject) {
        this.subject = subject;
    }

    public OPerson getPerson() {
        return person;
    }

    public void setPerson(OPerson person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OGrade oGrade = (OGrade) o;

        return id != null ? id.equals(oGrade.id) : oGrade.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
