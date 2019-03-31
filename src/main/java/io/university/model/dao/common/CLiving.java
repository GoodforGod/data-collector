package io.university.model.dao.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.number.GenUInteger;
import io.dummymaker.annotation.simple.string.GenId;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 11.03.2019
 */
@Entity
public class CLiving implements Serializable {

    @Id
    @GenId
    private String id;

    @GenTime
    private Timestamp enterTimestamp;

    @GenTime
    private Timestamp exitTimestamp;

    @GenUInteger
    private Integer enterCource;

    @GenUInteger
    private Integer invoiceCount;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_uid")
    private CPerson person;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "room_uid")
    private CRoom room;

    public String getId() {
        return id;
    }

    public Timestamp getEnterTimestamp() {
        return enterTimestamp;
    }

    public Timestamp getExitTimestamp() {
        return exitTimestamp;
    }

    public Integer getEnterCource() {
        return enterCource;
    }

    public Integer getInvoiceCount() {
        return invoiceCount;
    }

    public CPerson getPerson() {
        return person;
    }

    public void setPerson(CPerson person) {
        this.person = person;
    }

    public CRoom getRoom() {
        return room;
    }

    public void setRoom(CRoom room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CLiving living = (CLiving) o;

        return id != null ? id.equals(living.id) : living.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
