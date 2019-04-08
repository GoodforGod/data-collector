package io.university.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenSet;
import io.dummymaker.annotation.simple.number.GenUInteger;
import io.dummymaker.annotation.simple.string.GenCompany;
import io.dummymaker.annotation.simple.string.GenNick;
import io.dummymaker.annotation.simple.string.GenNoun;
import io.dummymaker.generator.simple.impl.EmbeddedGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 05.03.2019
 */
@Entity
public class CSpeciality implements Serializable {

    @Id
    @NotNull
    @GenUInteger
    private Integer code;

    @GenNick
    private String type;

    @GenNoun
    private String course;

    @GenCompany
    private String qualification;

    @JsonIgnore
    @GenSet(value = EmbeddedGenerator.class, depth = 8, max = 5)
    @OneToMany(mappedBy = "speciality", cascade = CascadeType.ALL)
    private Set<CSubject> subjects = new HashSet<>();

    @JsonIgnore
    @OneToOne(mappedBy = "speciality", cascade = CascadeType.ALL)
    private CStudy study;

    public Integer getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getCourse() {
        return course;
    }

    public String getQualification() {
        return qualification;
    }

    public Set<CSubject> getSubjects() {
        return subjects;
    }

    public CSubject addSubject(CSubject subject) {
        this.subjects.add(subject);
        return subject;
    }

    public CStudy getStudy() {
        return study;
    }

    public void setStudy(CStudy study) {
        this.study = study;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CSpeciality that = (CSpeciality) o;

        return code != null ? code.equals(that.code) : that.code == null;
    }

    @Override
    public int hashCode() {
        return code != null ? code.hashCode() : 0;
    }
}
