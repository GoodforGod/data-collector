package io.university.aggregator.model.dao.mysql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.string.GenId;
import io.dummymaker.annotation.simple.string.GenName;
import io.university.aggregator.model.dao.CPerson;
import io.university.api.model.IUpdatable;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 10.03.2019
 */
@Entity
public class CPublishment implements IUpdatable<CPublishment>, Serializable {

    @Id
    @GenId
    private String id;

    @GenName
    private String name;

    @GenTime
    private Timestamp publishTimestamp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "edition_uid")
    private CEdition edition;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_uid")
    private CPerson person;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Timestamp getPublishTimestamp() {
        return publishTimestamp;
    }

    public CPerson getPerson() {
        return person;
    }

    public void setPerson(CPerson person) {
        this.person = person;
    }

    public CEdition getEdition() {
        return edition;
    }

    public void setEdition(CEdition edition) {
        this.edition = edition;
    }

    @Override
    public void update(CPublishment cPublishment) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CPublishment that = (CPublishment) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
