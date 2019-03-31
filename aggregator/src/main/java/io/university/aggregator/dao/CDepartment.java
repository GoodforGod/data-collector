package io.university.aggregator.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.simple.string.GenCompany;

import javax.persistence.*;
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
public class CDepartment implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @GenCompany
    private String name;

    private Integer parentDepartmentId;

    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<CWorkHistory> workHistories = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<CStudy> studies = new ArrayList<>();

    public Integer getId() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CDepartment that = (CDepartment) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        return parentDepartmentId != null ? parentDepartmentId.equals(that.parentDepartmentId) : that.parentDepartmentId == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (parentDepartmentId != null ? parentDepartmentId.hashCode() : 0);
        return result;
    }
}
