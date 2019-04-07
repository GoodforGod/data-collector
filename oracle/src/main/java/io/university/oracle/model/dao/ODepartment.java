package io.university.oracle.model.dao;


import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.simple.string.GenCompany;
import io.dummymaker.annotation.simple.string.GenId;
import io.university.oracle.model.IUpdatable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class ODepartment implements IUpdatable<ODepartment>, Serializable {

    @Id
    @GenId
    private String id;

    @GenCompany
    private String name;

    private String subDepartmentId;

    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<OWorkHistory> workHistories = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<OStudy> studies = new ArrayList<>();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSubDepartmentId() {
        return subDepartmentId;
    }

    public OWorkHistory addWorkHistory(OWorkHistory workHistory) {
        this.workHistories.add(workHistory);
        return workHistory;
    }

    public OStudy addStudy(OStudy study) {
        this.studies.add(study);
        return study;
    }

    public List<OWorkHistory> getWorkHistories() {
        return workHistories;
    }

    public List<OStudy> getStudies() {
        return studies;
    }

    @Override
    public void update(ODepartment oDepartment) {
        this.name = oDepartment.getName();
        this.subDepartmentId = oDepartment.getSubDepartmentId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ODepartment that = (ODepartment) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
