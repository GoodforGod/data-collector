package io.university.aggregator.model.dao.mysql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenSet;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.string.GenId;
import io.dummymaker.annotation.simple.string.GenName;
import io.dummymaker.generator.simple.impl.EmbeddedGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
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
    @NotNull
    @GenId
    private String isbn;

    @GenName
    private String name;

    @GenTime
    private Timestamp publishTimestamp;

    @JsonIgnore
    @GenSet(value = EmbeddedGenerator.class, depth = 8)
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private Set<CReading> readings = new HashSet<>();

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

        return isbn != null ? isbn.equals(cBook.isbn) : cBook.isbn == null;
    }

    @Override
    public int hashCode() {
        return isbn != null ? isbn.hashCode() : 0;
    }
}
