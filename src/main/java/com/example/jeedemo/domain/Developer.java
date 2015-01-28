package com.example.jeedemo.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "developer.all", query = "Select d from Developer d"),
        @NamedQuery(name = "developer.house", query = "select d from Developer d join d.houses h WHERE h.id = :houseId")
})
public class Developer {

    private Long id;
    private String name = "";
    private String nip = "";

    private List<House> houses = new ArrayList<House>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Size(min = 2, max = 30)
    public String getName() {
        return name;
    }

    public void setName(String firstName) {
        this.name = firstName;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    @Size(min = 10, max = 10)
    public String getNip() {
        return nip;
    }

    public void setNip(String pesel) {
        this.nip = pesel;
    }
}
