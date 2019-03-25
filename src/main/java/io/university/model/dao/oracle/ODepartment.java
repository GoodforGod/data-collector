package io.university.model.dao.oracle;


import io.dummymaker.annotation.simple.string.GenCompany;

import javax.persistence.*;
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
public class ODepartment implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @GenCompany
    private String name;

    private Integer subDepartmentId;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<OWorkHistory> workHistories = new ArrayList<>();

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<OStudy> studies = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSubDepartmentId() {
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
}
