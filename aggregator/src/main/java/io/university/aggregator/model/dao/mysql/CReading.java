package io.university.aggregator.model.dao.mysql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.string.GenId;
import io.university.aggregator.model.dao.CPerson;

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
public class CReading implements Serializable {

    @Id
    @GenId
    private String id;

    @GenTime
    private Timestamp takeTimestamp;

    @GenTime
    private Timestamp returnTimestamp;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_uid")
    private CBook book;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_uid")
    private CPerson person;

    public String getId() {
        return id;
    }

    public Timestamp getTakeTimestamp() {
        return takeTimestamp;
    }

    public Timestamp getReturnTimestamp() {
        return returnTimestamp;
    }

    public CBook getBook() {
        return book;
    }

    public void setBook(CBook book) {
        this.book = book;
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

        CReading cReading = (CReading) o;

        return id != null ? id.equals(cReading.id) : cReading.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
