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
public class OSpeciality implements Serializable {

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

    @GenList(value = EmbeddedGenerator.class, depth = 8)
    @OneToMany(mappedBy = "speciality", cascade = CascadeType.ALL)
    private List<OSubject> subjects = new ArrayList<>();

    @OneToOne(mappedBy = "speciality", cascade = CascadeType.ALL)
    private OStudy study;

    public Integer getCode() {
        return code;
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

    public List<OSubject> getSubjects() {
        return subjects;
    }

    public OSubject addSubject(OSubject subject) {
        this.subjects.add(subject);
        return subject;
    }

    public OStudy getStudy() {
        return study;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OSpeciality that = (OSpeciality) o;

        return code != null ? code.equals(that.code) : that.code == null;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
}
