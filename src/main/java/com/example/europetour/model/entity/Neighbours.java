package com.example.europetour.model.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Set;

@Entity
public class Neighbours extends BaseEntity {
    private Country country;
    private Country neighbour;

    @ManyToOne
    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @ManyToOne
    public Country getNeighbour() {
        return neighbour;
    }

    public void setNeighbour(Country neighbour) {
        this.neighbour = neighbour;
    }
}
