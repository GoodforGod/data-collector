package io.university.provider.model.dao;

import io.dummymaker.annotation.complex.GenEnum;
import io.dummymaker.annotation.complex.GenList;
import io.dummymaker.annotation.simple.number.GenUInteger;
import io.dummymaker.annotation.simple.string.GenCompany;
import io.dummymaker.annotation.simple.string.GenNoun;
import io.dummymaker.generator.simple.impl.EmbeddedGenerator;

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
    @GeneratedValue
    private int id;

    @GenUInteger
    private int code;

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

    public int getId() {
        return id;
    }

    public int getCode() {
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
}
