package io.university.oracle.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenEnum;
import io.dummymaker.annotation.complex.GenSet;
import io.dummymaker.annotation.simple.number.GenUInteger;
import io.dummymaker.annotation.simple.string.GenCompany;
import io.dummymaker.annotation.simple.string.GenNoun;
import io.dummymaker.generator.simple.impl.EmbeddedGenerator;
import io.university.oracle.model.IUpdatable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Entity
public class OSpeciality implements IUpdatable<OSpeciality>, Serializable {

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

    @JsonIgnore
    @GenSet(value = EmbeddedGenerator.class, depth = 8)
    @OneToMany(mappedBy = "speciality", cascade = CascadeType.ALL)
    private Set<OSubject> subjects = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "speciality", cascade = CascadeType.ALL)
    private Set<OStudy> studies = new HashSet<>();

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

    public Set<OSubject> getSubjects() {
        return subjects;
    }

    public OSubject addSubject(OSubject subject) {
        this.subjects.add(subject);
        return subject;
    }

    public Set<OStudy> getStudies() {
        return studies;
    }

    public OStudy addStudy(OStudy study) {
        this.studies.add(study);
        return study;
    }

    @Override
    public void update(OSpeciality oSpeciality) {
        this.type = oSpeciality.getType();
        this.course = oSpeciality.getCourse();
        this.qualification = oSpeciality.getQualification();
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
