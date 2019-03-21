package io.university.provider.model.dao;

import io.dummymaker.annotation.complex.GenEnum;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.string.GenCity;
import io.dummymaker.annotation.simple.string.GenName;
import io.dummymaker.annotation.simple.string.GenSurname;
import io.dummymaker.annotation.special.GenEmbedded;

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
public class OPerson implements Serializable {

    public enum PersonType {
        STUDENT,
        TEACHER
    }

    @Id
    @GeneratedValue
    private Integer id;

    @GenName
    private String name;

    @GenName
    private String middleName;

    @GenSurname
    private String surname;

    @GenTime
    private Timestamp birthTimestamp;

    @GenCity
    private String birthPlace;

    @GenEnum
    private PersonType type;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "schedule_mapper",
            joinColumns = { @JoinColumn(name = "person_id") },
            inverseJoinColumns = { @JoinColumn(name = "schedule_id") }
    )
    private Set<OSchedule> schedules = new HashSet<>();

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<OGrade> grades = new HashSet<>();

    @GenEmbedded
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private OWorkHistory workHistory;

    @GenEmbedded
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private OStudy study;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getSurname() {
        return surname;
    }

    public Timestamp getBirthTimestamp() {
        return birthTimestamp;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public OStudy getStudy() {
        return study;
    }

    public PersonType getType() {
        return type;
    }

    public OWorkHistory getWorkHistory() {
        return workHistory;
    }

    public Set<OSchedule> getSchedules() {
        return schedules;
    }

    public OSchedule addSchedule(OSchedule schedule) {
        this.schedules.add(schedule);
        return schedule;
    }

    public Set<OGrade> getGrades() {
        return grades;
    }

    public OGrade addGrade(OGrade grade) {
        this.grades.add(grade);
        grade.setPerson(this);
        return grade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OPerson person = (OPerson) o;

        if (id != person.id) return false;
        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (middleName != null ? !middleName.equals(person.middleName) : person.middleName != null) return false;
        if (surname != null ? !surname.equals(person.surname) : person.surname != null) return false;
        if (birthTimestamp != null ? !birthTimestamp.equals(person.birthTimestamp) : person.birthTimestamp != null)
            return false;
        if (birthPlace != null ? !birthPlace.equals(person.birthPlace) : person.birthPlace != null) return false;
        if (type != person.type) return false;
        if (workHistory != null ? !workHistory.equals(person.workHistory) : person.workHistory != null) return false;
        return study != null ? study.equals(person.study) : person.study == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (birthTimestamp != null ? birthTimestamp.hashCode() : 0);
        result = 31 * result + (birthPlace != null ? birthPlace.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (workHistory != null ? workHistory.hashCode() : 0);
        result = 31 * result + (study != null ? study.hashCode() : 0);
        return result;
    }
}
