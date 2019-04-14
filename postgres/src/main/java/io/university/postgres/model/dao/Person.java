package io.university.postgres.model.dao;

import io.dummymaker.annotation.complex.GenEnum;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.string.GenCity;
import io.dummymaker.annotation.simple.string.GenName;
import io.dummymaker.annotation.simple.string.GenSurname;
import io.dummymaker.annotation.special.GenEmbedded;
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
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"name", "middleName", "surname", "birthTimestamp", "birthPlace"})
})
public class Person implements IUpdatable<Person>, Serializable {

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

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL)
    private Set<Grade> grades = new HashSet<>();

    @GenEmbedded
    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL)
    private Study study;

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

    public Study getStudy() {
        return study;
    }

    public PersonType getType() {
        return type;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public Grade addGrade(Grade grade) {
        this.grades.add(grade);
        grade.setPerson(this);
        return grade;
    }

    @Override
    public void update(Person person) {
        this.type = person.getType();
        this.grades.addAll(person.getGrades());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (name != null ? !name.equals(person.name) : person.name != null) return false;
        if (middleName != null ? !middleName.equals(person.middleName) : person.middleName != null) return false;
        if (surname != null ? !surname.equals(person.surname) : person.surname != null) return false;
        if (birthTimestamp != null ? !birthTimestamp.equals(person.birthTimestamp) : person.birthTimestamp != null)
            return false;
        return birthPlace != null ? birthPlace.equals(person.birthPlace) : person.birthPlace == null;
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
