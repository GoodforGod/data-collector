package io.university.model.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenEnum;
import io.dummymaker.annotation.complex.GenSet;
import io.dummymaker.annotation.simple.number.GenUInteger;
import io.dummymaker.annotation.simple.string.GenCity;
import io.dummymaker.annotation.simple.string.GenCountry;
import io.dummymaker.annotation.simple.string.GenId;
import io.dummymaker.annotation.simple.string.GenName;
import io.dummymaker.generator.simple.impl.EmbeddedGenerator;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

/**
 * ! NO DESCRIPTION !
 *
 * @author GoodforGod
 * @since 10.03.2019
 */
@Entity
public class CEdition {

    public enum Lang {
        EN,
        RU,
        CH,
        FR
    }

    @Id
    @NotNull
    @GenId
    private String id;

    @GenName
    private String name;

    @GenEnum
    private Lang lang;

    @GenCity
    private String city;

    @GenUInteger
    private Integer pages;

    @GenCountry
    private String type;

    @JsonIgnore
    @GenSet(value = EmbeddedGenerator.class, depth = 8)
    @OneToMany(mappedBy = "edition", cascade = CascadeType.ALL)
    private Set<CPublishment> publishments = new HashSet<>();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Lang getLang() {
        return lang;
    }

    public String getCity() {
        return city;
    }

    public Integer getPages() {
        return pages;
    }

    public String getType() {
        return type;
    }

    public Set<CPublishment> getPublishments() {
        return publishments;
    }

    public CPublishment addPublishment(CPublishment publishment) {
        this.publishments.add(publishment);
        publishment.setEdition(this);
        return publishment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CEdition cEdition = (CEdition) o;

        return id != null ? id.equals(cEdition.id) : cEdition.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
