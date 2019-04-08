package io.university.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenSet;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.number.GenUByte;
import io.dummymaker.annotation.simple.number.GenUInteger;
import io.dummymaker.annotation.simple.string.GenCountry;
import io.dummymaker.annotation.special.GenEmbedded;
import io.dummymaker.generator.simple.impl.EmbeddedGenerator;

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
public class CSubject implements Serializable {

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
    @OneToOne(mappedBy = "subject", cascade = CascadeType.ALL)
    private CSchedule schedule;

    @JsonIgnore
    @GenSet(value = EmbeddedGenerator.class, depth = 8, max = 6)
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<CGrade> grades = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "speciality_uid")
    private CSpeciality speciality;

    public CSpeciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(CSpeciality speciality) {
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

    public CSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(CSchedule schedule) {
        this.schedule = schedule;
    }

    public Set<CGrade> getGrades() {
        return grades;
    }

    public CGrade addGrade(CGrade grade) {
        this.grades.add(grade);
        return grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CSubject cSubject = (CSubject) o;

        if (code != null ? !code.equals(cSubject.code) : cSubject.code != null) return false;
        return semester != null ? semester.equals(cSubject.semester) : cSubject.semester == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (semester != null ? semester.hashCode() : 0);
        return result;
    }
}
