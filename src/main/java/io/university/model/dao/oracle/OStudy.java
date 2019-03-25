package io.university.model.dao.oracle;

import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.number.GenUShort;
import io.dummymaker.annotation.simple.string.GenHexNumber;

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
public class OStudy implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @GenHexNumber
    private String course;

    @GenUShort
    private String groupNum;

    @GenTime
    private Timestamp startTimestamp;

    @GenTime
    private Timestamp graduateTimestamp;

    @OneToOne
    @JoinColumn(name = "person_uid")
    private OPerson person;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_uid")
    private ODepartment department;

    @OneToOne
    @JoinColumn(name = "speciality_uid")
    private OSpeciality speciality;

    public void setSpeciality(OSpeciality speciality) {
        this.speciality = speciality;
    }

    public OSpeciality getSpeciality() {
        return speciality;
    }

    public int getId() {
        return id;
    }

    public String getGroupNum() {
        return groupNum;
    }

    public void setDepartment(ODepartment department) {
        this.department = department;
    }

    public ODepartment getDepartment() {
        return department;
    }

    public String getCourse() {
        return course;
    }

    public Timestamp getStartTimestamp() {
        return startTimestamp;
    }

    public Timestamp getGraduateTimestamp() {
        return graduateTimestamp;
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

        OStudy oStudy = (OStudy) o;

        if (id != null ? !id.equals(oStudy.id) : oStudy.id != null) return false;
        if (course != null ? !course.equals(oStudy.course) : oStudy.course != null) return false;
        if (groupNum != null ? !groupNum.equals(oStudy.groupNum) : oStudy.groupNum != null) return false;
        if (startTimestamp != null ? !startTimestamp.equals(oStudy.startTimestamp) : oStudy.startTimestamp != null)
            return false;
        return graduateTimestamp != null ? graduateTimestamp.equals(oStudy.graduateTimestamp) : oStudy.graduateTimestamp == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (course != null ? course.hashCode() : 0);
        result = 31 * result + (groupNum != null ? groupNum.hashCode() : 0);
        result = 31 * result + (startTimestamp != null ? startTimestamp.hashCode() : 0);
        result = 31 * result + (graduateTimestamp != null ? graduateTimestamp.hashCode() : 0);
        return result;
    }
}
