package io.university.provider.model.dao;

import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.string.GenNick;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 16.02.2019
 */
@Entity
public class OWorkHistory implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @GenTime
    private Timestamp startTimestamp;

    @GenTime
    private Timestamp endTimestamp;

    @GenNick
    private String position;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_uid")
    private ODepartment department;

    @OneToOne
    @JoinColumn(name = "person_uid")
    private OPerson person;

    public int getId() {
        return id;
    }

    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    public Timestamp getEndTimestamp() {
        return endTimestamp;
    }

    public String getPosition() {
        return position;
    }

    public ODepartment getDepartment() {
        return department;
    }

    public void setDepartment(ODepartment department) {
        this.department = department;
    }

    public OPerson getPerson() {
        return person;
    }

    public void setPerson(OPerson person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OWorkHistory that = (OWorkHistory) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return position != null ? position.equals(that.position) : that.position == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}
