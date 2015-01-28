package com.example.jeedemo.domain;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "house.all", query = "select h from House h"),
        @NamedQuery(name = "house.unsold", query = "Select h from House h where h.sold = false"),
        @NamedQuery(name = "house.when", query = "Select h from House h where h.dateOfConstruction > :date"),
})
public class House {
    private Long id;

    private String color = "";
    private int nrOfFloors;
    private String address ="";
    private Date dateOfConstruction = new Date();
    private String imageUrl = "";
    private Boolean sold = false;
//    private Developer developer = new Developer();


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Pattern(regexp = "blue|red|white|brown|green|yellow")
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @DecimalMin(value = "1")
    public int getNrOfFloors() {
        return nrOfFloors;
    }

    public void setNrOfFloors(int nrOfFloors) {
        this.nrOfFloors = nrOfFloors;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Past
    public Date getDateOfConstruction() {
        return dateOfConstruction;
    }

    public void setDateOfConstruction(Date dateOfConstruction) {
        this.dateOfConstruction = dateOfConstruction;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getSold() {
        return sold;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
    }

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    public Developer getDeveloper() {
//        return developer;
//    }
//
//    public void setDeveloper(Developer developer) {
//        this.developer = developer;
//    }
}
