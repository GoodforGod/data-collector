package io.university.model.dao.common;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.dummymaker.annotation.complex.GenSet;
import io.dummymaker.annotation.complex.GenTime;
import io.dummymaker.annotation.simple.GenBoolean;
import io.dummymaker.annotation.simple.number.GenUByte;
import io.dummymaker.annotation.simple.number.GenUShort;
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
 * @since 11.03.2019
 */
@Entity
public class CRoom implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    @GenUShort
    private String roomNumber;

    @GenUByte
    private Integer peopleCapacity;

    @GenUByte
    private Integer peopleLiving;

    @GenTime
    private Timestamp desinfectionTimestamp;

    @GenBoolean
    private Boolean haveInsects;

    @GenSet(value = EmbeddedGenerator.class, depth = 8, max = 6)
    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL)
    private Set<CLiving> livings = new HashSet<>();

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "community_uid")
    private CCommunity community;

    public Integer getId() {
        return id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public Integer getPeopleCapacity() {
        return peopleCapacity;
    }

    public Integer getPeopleLiving() {
        return peopleLiving;
    }

    public Timestamp getDesinfectionTimestamp() {
        return desinfectionTimestamp;
    }

    public Boolean getHaveInsects() {
        return haveInsects;
    }

    public Set<CLiving> getLivings() {
        return livings;
    }

    public CLiving addLiving(CLiving living) {
        this.livings.add(living);
        return living;
    }

    public CCommunity getCommunity() {
        return community;
    }

    public void setCommunity(CCommunity community) {
        this.community = community;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CRoom room = (CRoom) o;

        if (id != null ? !id.equals(room.id) : room.id != null) return false;
        if (roomNumber != null ? !roomNumber.equals(room.roomNumber) : room.roomNumber != null) return false;
        return peopleCapacity != null ? peopleCapacity.equals(room.peopleCapacity) : room.peopleCapacity == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (roomNumber != null ? roomNumber.hashCode() : 0);
        result = 31 * result + (peopleCapacity != null ? peopleCapacity.hashCode() : 0);
        return result;
    }
}
