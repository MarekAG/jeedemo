package com.example.jeedemo.domain;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "client.all", query = "Select c from Client c"),
        @NamedQuery(name = "client.like", query = "select c from  Client c WHERE c.lastName like :lastName")
})
public class Client {

    private Long id;
    private String firstName = "";
    private String lastName = "";
    private String pesel = "";

    private List<House> houses = new ArrayList<House>();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Size(min = 3, max = 30)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Size(min = 3, max = 30)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

    @Size(min = 11, max = 11)
    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }
}
