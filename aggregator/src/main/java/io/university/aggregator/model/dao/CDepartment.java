package io.university.aggregator.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.simple.string.GenCompany;
import io.dummymaker.annotation.simple.string.GenId;
import io.university.api.model.IUpdatable;

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
 * @since 05.03.2019
 */
@Entity
public class CDepartment implements IUpdatable<CDepartment>, Serializable {

    @Id
    @GenId
    private String id;

    @GenCompany
    private String name;

    private Integer parentDepartmentId;

    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<CWorkHistory> workHistories = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<CStudy> studies = new ArrayList<>();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getParentDepartmentId() {
        return parentDepartmentId;
    }

    public CWorkHistory addWorkHistory(CWorkHistory workHistory) {
        this.workHistories.add(workHistory);
        return workHistory;
    }

    public CStudy addStudy(CStudy study) {
        this.studies.add(study);
        return study;
    }

    public List<CWorkHistory> getWorkHistories() {
        return workHistories;
    }

    public List<CStudy> getStudies() {
        return studies;
    }

    @Override
    public void update(CDepartment department) {
        this.name = department.getName();
        this.parentDepartmentId = department.getParentDepartmentId();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CDepartment that = (CDepartment) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
