package io.university.postgres.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenEnum;
import io.dummymaker.annotation.complex.GenSet;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.number.GenUByte;
import io.dummymaker.annotation.simple.number.GenUInteger;
import io.dummymaker.annotation.simple.string.GenCountry;
import io.dummymaker.generator.simple.impl.EmbeddedGenerator;
import io.university.postgres.model.IUpdatable;

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
public class Subject implements IUpdatable<Subject>, Serializable {

    private enum SubjectType {
        LECTURE,
        LABORATORY,
        PRACTICE
    }

    @Id
    @GenUInteger
    private Integer code;

    @GenCountry
    private String name;

    @GenUByte
    private String semester;

    @GenEnum
    private SubjectType type;

    @GenTime
    private Timestamp startTimestamp;

    @GenTime
    private Timestamp endTimestamp;

    @JsonIgnore
    @GenSet(value = EmbeddedGenerator.class, depth = 7, max = 5)
    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL)
    private Set<Grade> grades = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "speciality_uid")
    private Speciality speciality;

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
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

    public SubjectType getType() {
        return type;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public Grade addGrade(Grade grade) {
        this.grades.add(grade);
        return grade;
    }

    @Override
    public void update(Subject subject) {
        this.name = subject.getName();
        this.type = subject.getType();
        this.semester = subject.getSemester();
        this.endTimestamp = subject.getEndTimestamp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subject subject = (Subject) o;

        if (code != null ? !code.equals(subject.code) : subject.code != null) return false;
        return semester != null ? semester.equals(subject.semester) : subject.semester == null;
    }

    @Override
    public int hashCode() {
        int result = code != null ? code.hashCode() : 0;
        result = 31 * result + (semester != null ? semester.hashCode() : 0);
        return result;
    }
}

