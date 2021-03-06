package io.university.aggregator.model.dao.mysql;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenSet;
import io.dummymaker.annotation.simple.string.GenId;
import io.dummymaker.annotation.simple.string.GenNick;
import io.dummymaker.annotation.simple.string.GenPhrase;
import io.dummymaker.generator.simple.impl.EmbeddedGenerator;
import io.university.api.model.IUpdatable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
public class CProject implements IUpdatable<CProject>, Serializable {

    @Id
    @GenId
    private String id;

    @GenNick
    private String name;

    @GenPhrase
    private String description;

    @JsonIgnore
    @GenSet(value = EmbeddedGenerator.class, depth = 8)
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private Set<CProjectParticipation> participations = new HashSet<>();

    public String getId() {
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
    public void update(CProject cProject) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CProject cProject = (CProject) o;

        return id != null ? id.equals(cProject.id) : cProject.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
