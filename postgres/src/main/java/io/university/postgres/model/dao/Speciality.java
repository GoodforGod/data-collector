package io.university.postgres.model.dao;

import io.dummymaker.annotation.complex.GenEnum;
import io.dummymaker.annotation.complex.GenList;
import io.dummymaker.annotation.simple.number.GenUInteger;
import io.dummymaker.annotation.simple.string.GenCompany;
import io.dummymaker.annotation.simple.string.GenNoun;
import io.dummymaker.generator.simple.impl.EmbeddedGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Entity
public class Speciality implements Serializable {

    public enum SpecialityType {
        BACHELOR,
        MASTER
    }

    @Id
    @NotNull
    @GenUInteger
    private Integer code;

    @GenEnum
    private SpecialityType type;

    @GenNoun
    private String course;

    @GenCompany
    private String qualification;

    @GenCompany
    private String university;

    @GenList(value = EmbeddedGenerator.class, depth = 8)
    @OneToMany(mappedBy = "speciality", cascade = CascadeType.ALL)
    private List<Subject> subjects = new ArrayList<>();

    @OneToOne(mappedBy = "speciality", cascade = CascadeType.ALL)
    private Study study;

    public Integer getCode() {
        return code;
    }

    public String getUniversity() {
        return university;
    }

    public SpecialityType getType() {
        return type;
    }

    public String getCourse() {
        return course;
    }

    public String getQualification() {
        return qualification;
    }

    public List<Subject> getSubjects() {
        return subjects;
    }

    public Subject addSubject(Subject subject) {
        this.subjects.add(subject);
        return subject;
    }

    public Study getStudy() {
        return study;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Speciality that = (Speciality) o;

        return code != null ? code.equals(that.code) : that.code == null;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
}
