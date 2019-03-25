package io.university.model.dao.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenSet;
import io.dummymaker.annotation.simple.string.GenNick;
import io.dummymaker.annotation.simple.string.GenPhrase;
import io.dummymaker.generator.simple.impl.EmbeddedGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 10.03.2019
 */
@Entity
public class CProject implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @GenNick
    private String name;

    @GenPhrase
    private String description;

    @JsonIgnore
    @GenSet(value = EmbeddedGenerator.class, depth = 8)
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<CProjectParticipation> participations = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<CProjectParticipation> getParticipations() {
        return participations;
    }

    public CProjectParticipation addPraticipation(CProjectParticipation participation) {
        this.participations.add(participation);
        return participation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CProject cProject = (CProject) o;

        if (id != null ? !id.equals(cProject.id) : cProject.id != null) return false;
        if (name != null ? !name.equals(cProject.name) : cProject.name != null) return false;
        return description != null ? description.equals(cProject.description) : cProject.description == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
