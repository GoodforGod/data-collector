package io.university.postgres.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.number.GenUShort;
import io.dummymaker.annotation.simple.string.GenHexNumber;
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
public class Study implements IUpdatable<Study>, Serializable {

    @Id
    @GenId
    private String id;

    @GenHexNumber
    private String course;

    @GenUShort
    private String groupNum;

    @GenTime
    private Timestamp startTimestamp;

    @GenTime
    private Timestamp graduateTimestamp;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "person_uid")
    private Person person;

    @ManyToOne
    @JoinColumn(name = "speciality_uid")
    private Speciality speciality;

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public String getId() {
        return id;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public String getCourse() {
        return course;
    }

    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    public Timestamp getGraduateTimestamp() {
        return graduateTimestamp;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public void update(Study study) {
        this.course = study.getCourse();
        this.groupNum = study.getGroupNum();
        this.graduateTimestamp = study.getGraduateTimestamp();
        if(study.getSpeciality() != null) {
            this.speciality = study.getSpeciality();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Study study = (Study) o;

        return id != null ? id.equals(study.id) : study.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
