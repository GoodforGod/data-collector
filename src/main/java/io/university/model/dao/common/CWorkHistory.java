package io.university.model.dao.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.string.GenNick;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 05.03.2019
 */
@Entity
public class CWorkHistory implements Serializable {

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
    private CDepartment department;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_uid")
    private CPerson person;

    public Integer getId() {
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

    public CDepartment getDepartment() {
        return department;
    }

    public void setDepartment(CDepartment department) {
        this.department = department;
    }

    public CPerson getPerson() {
        return person;
    }

    public void setPerson(CPerson person) {
        this.person = person;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CWorkHistory that = (CWorkHistory) o;

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
