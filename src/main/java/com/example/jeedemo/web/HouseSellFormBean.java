package com.example.jeedemo.web;

import com.example.jeedemo.domain.Client;
import com.example.jeedemo.domain.Developer;
import com.example.jeedemo.domain.House;
import com.example.jeedemo.service.ClientManager;
import com.example.jeedemo.service.DeveloperManager;
import com.example.jeedemo.service.HouseManager;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;

@SessionScoped
@Named(value = "houseSellBean")
public class HouseSellFormBean implements Serializable {


    @Inject
    ClientManager cm;

    @Inject
    HouseManager hm;

    @Inject
    DeveloperManager dm;

    private Long clientId;
    private Long houseId;

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public List<House> getUnsoldHouses() {
        return hm.getUnsoldHouses();
    }

    public List<Client> getAllCLients() {
        return cm.getAllClients();
    }

    public List<Developer> getAllDevelopers() {
        return dm.getAllDevelopers();
    }

    public String sellHouse() {
        hm.sellHouse(clientId, houseId);
        return null;
    }
}
