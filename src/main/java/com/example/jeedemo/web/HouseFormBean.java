package com.example.jeedemo.web;

import com.example.jeedemo.domain.Developer;
import com.example.jeedemo.domain.House;
import com.example.jeedemo.service.ClientManager;
import com.example.jeedemo.service.DeveloperManager;
import com.example.jeedemo.service.HouseManager;

import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@SessionScoped
@Named(value = "houseBean")
public class HouseFormBean implements Serializable{


    private House house = new House();
    ListDataModel<House> houses = new ListDataModel<House>();

    private House houseToShow = new House();
    ListDataModel<House> houseHouses = new ListDataModel<House>();

    @Inject
    DeveloperManager dm;

    @Inject
    ClientManager cm;

    @Inject
    HouseManager hm;

    Long developerId;

    public Long getDeveloperId() {
        return developerId;
    }

    public void setDeveloperId(Long developerId) {
        this.developerId = developerId;
    }

    public House getHouse() {
        return house;
    }

    public void setHouse(House house) {
        this.house = house;
    }

    public ListDataModel<House> getAllHouses() {
        houses.setWrappedData(hm.getAllHouses());
        return houses;
    }


    public String addHouse() {
        hm.addHouse(house);
        dm.addHouse(developerId, house);
        return "showHouses";
    }

    public String deleteHouse() {
        House houseToDelete = houses.getRowData();
        dm.removeHouse(houseToDelete);
        cm.removeHouse(houseToDelete);
        hm.deleteHouse(houseToDelete);
        return null;
    }

    public String showHouseDetails() {
        houseToShow = houses.getRowData();
        return "houseDetails";
    }

    public void uniqueAddress(FacesContext context, UIComponent component,
                            Object value) {

        String address = (String) value;

        for (House house  : hm.getAllHouses()) {
            if (house.getAddress().equalsIgnoreCase(address)) {
                FacesMessage message = new FacesMessage(
                        "House with this address already exists in database");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
        }
    }

}
