package io.university.oracle.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenSet;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.number.GenUByte;
import io.dummymaker.annotation.simple.number.GenUInteger;
import io.dummymaker.annotation.simple.string.GenCountry;
import io.dummymaker.annotation.special.GenEmbedded;
import io.dummymaker.generator.simple.impl.EmbeddedGenerator;
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
public class OSubject implements IUpdatable<OSubject>, Serializable {

    @Id
    @GenUInteger
    private Integer code;

    @GenCountry
    private String name;

    @GenUByte
    private String semester;

    @GenTime
    private Timestamp startTimestamp;

    @GenTime
    private Timestamp endTimestamp;

    @JsonIgnore
    @GenEmbedded(depth = 7)
    @OneToOne(mappedBy = "subject")
    private OSchedule schedule;

    @JsonIgnore
    @GenSet(value = EmbeddedGenerator.class, depth = 7, max = 5)
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<OGrade> grades = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "speciality_uid")
    private OSpeciality speciality;

    public OSpeciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(OSpeciality speciality) {
        this.speciality = speciality;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getSemester() {
        return semester;
    }

    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    public Timestamp getEndTimestamp() {
        return endTimestamp;
    }

    public OSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(OSchedule schedule) {
        this.schedule = schedule;
    }

    public Set<OGrade> getGrades() {
        return grades;
    }

    public OGrade addGrade(OGrade grade) {
        this.grades.add(grade);
        return grade;
    }

    @Override
    public void update(OSubject oSubject) {
        this.name = oSubject.getName();
        this.semester = oSubject.getSemester();
        this.endTimestamp = oSubject.getEndTimestamp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OSubject oSubject = (OSubject) o;

        if (code != null ? !code.equals(oSubject.code) : oSubject.code != null) return false;
        return semester != null ? semester.equals(oSubject.semester) : oSubject.semester == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (semester != null ? semester.hashCode() : 0);
        return result;
    }
}

