package io.university.aggregator.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenSet;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.GenUuid;
import io.dummymaker.annotation.simple.string.GenName;
import io.dummymaker.generator.simple.impl.EmbeddedGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 10.03.2019
 */
@Entity
public class CBook implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @GenUuid
    private String isbn;

    @GenName
    private String name;

    @GenTime
    private Timestamp publishTimestamp;

    @JsonIgnore
    @GenSet(value = EmbeddedGenerator.class, depth = 8)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<CReading> readings = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public Timestamp getPublishTimestamp() {
        return publishTimestamp;
    }

    public Set<CReading> getReadings() {
        return readings;
    }

    public CReading addReading(CReading reading) {
        this.readings.add(reading);
        return reading;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CBook cBook = (CBook) o;

        if (id != null ? !id.equals(cBook.id) : cBook.id != null) return false;
        if (isbn != null ? !isbn.equals(cBook.isbn) : cBook.isbn != null) return false;
        if (name != null ? !name.equals(cBook.name) : cBook.name != null) return false;
        return publishTimestamp != null ? publishTimestamp.equals(cBook.publishTimestamp) : cBook.publishTimestamp == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (publishTimestamp != null ? publishTimestamp.hashCode() : 0);
        return result;
    }
}
