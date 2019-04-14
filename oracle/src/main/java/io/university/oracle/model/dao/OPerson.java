package io.university.oracle.model.dao;

import io.dummymaker.annotation.complex.GenEnum;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.string.GenCity;
import io.dummymaker.annotation.simple.string.GenName;
import io.dummymaker.annotation.simple.string.GenSurname;
import io.dummymaker.annotation.special.GenEmbedded;
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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "middleName", "surname", "birthTimestamp", "birthPlace"})
})
public class OPerson implements IUpdatable<OPerson>, Serializable {

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
            name = "oschedule_mapper",
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
    public void update(OPerson oPerson) {
        this.type = oPerson.getType();
        this.grades.addAll(oPerson.getGrades());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OPerson oPerson = (OPerson) o;

        if (name != null ? !name.equals(oPerson.name) : oPerson.name != null) return false;
        if (middleName != null ? !middleName.equals(oPerson.middleName) : oPerson.middleName != null) return false;
        if (surname != null ? !surname.equals(oPerson.surname) : oPerson.surname != null) return false;
        if (birthTimestamp != null ? !birthTimestamp.equals(oPerson.birthTimestamp) : oPerson.birthTimestamp != null)
            return false;
        return birthPlace != null ? birthPlace.equals(oPerson.birthPlace) : oPerson.birthPlace == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (middleName != null ? middleName.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + (birthTimestamp != null ? birthTimestamp.hashCode() : 0);
        result = 31 * result + (birthPlace != null ? birthPlace.hashCode() : 0);
        return result;
    }
}
