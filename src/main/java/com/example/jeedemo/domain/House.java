package com.example.jeedemo.domain;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import java.time.Instant;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "house.all", query = "select h from House h"),
        @NamedQuery(name = "house.unsold", query = "Select h from House h where h.sold = false"),
        @NamedQuery(name = "house.buildDate", query = "Select h from House h where h.dateOfConstruction > :date"),
        @NamedQuery(name = "house.newest", query = "Select h from House h order by h.dateOfAdding desc")
})
public class House {
    private Long id;

    private String color = "";
    private int nrOfFloors;
    private String address ="";
    private Date dateOfConstruction = new Date();
    private Date dateOfAdding = Date.from(Instant.now());
    private String imageUrl = "";
    private Boolean sold = false;

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

    @Temporal(TemporalType.DATE)
    @Column(nullable = true)
    public Date getDateOfAdding() {
        return dateOfAdding;
    }

    public void setDateOfAdding(Date dateOfAdding) {
        this.dateOfAdding = dateOfAdding;
    }
}
