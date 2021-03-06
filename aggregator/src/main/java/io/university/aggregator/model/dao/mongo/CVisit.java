package io.university.aggregator.model.dao.mongo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.string.GenId;
import io.university.aggregator.model.dao.CPerson;
import io.university.api.model.IUpdatable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 11.03.2019
 */
@Entity
public class CVisit implements IUpdatable<CVisit>, Serializable {

    @Id
    @NotNull
    @GenId
    private String id;

    @GenTime
    private Timestamp enterTimestamp;

    @GenTime
    private Timestamp exitTimestamp;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_uid")
    private CPerson person;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "community_uid")
    private CCommunity community;

    public String getId() {
        return id;
    }

    public Timestamp getEnterTimestamp() {
        return enterTimestamp;
    }

    public Timestamp getExitTimestamp() {
        return exitTimestamp;
    }

    public CPerson getPerson() {
        return person;
    }

    public void setPerson(CPerson person) {
        this.person = person;
    }

    public CCommunity getCommunity() {
        return community;
    }

    public void setCommunity(CCommunity community) {
        this.community = community;
    }

    @Override
    public void update(CVisit cVisit) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CVisit visit = (CVisit) o;

        return id != null ? id.equals(visit.id) : visit.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
