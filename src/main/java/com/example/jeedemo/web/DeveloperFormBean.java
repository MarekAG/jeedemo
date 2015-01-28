package com.example.jeedemo.web;

import com.example.jeedemo.domain.Developer;
import com.example.jeedemo.domain.House;
import com.example.jeedemo.service.DeveloperManager;
import com.example.jeedemo.service.HouseManager;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.model.ListDataModel;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named(value = "developerBean")
public class DeveloperFormBean implements Serializable {

    private Developer developer = new Developer();
    ListDataModel<Developer> developers = new ListDataModel<Developer>();

    private Developer developerToShow = new Developer();
    ListDataModel<House> developerHouses = new ListDataModel<House>();

    @Inject
    DeveloperManager dm;

    @Inject
    HouseManager hm;

    public Developer getDeveloper() {
        return developer;
    }

    public void setDeveloper(Developer developer) {
        this.developer = developer;
    }

    public ListDataModel<Developer> getAllDevelopers() {
        developers.setWrappedData(dm.getAllDevelopers());
        return developers;
    }

    public ListDataModel<House> getDeveloperHouses() {
        developerHouses.setWrappedData(dm.getDeveloperHouses(developerToShow));
        return developerHouses;
    }

    public String addDeveloper() {
        dm.addDeveloper(developer);
        return "showDevelopers";
    }

    public String deleteDeveloper() {
        Developer developerToDelete = developers.getRowData();
        dm.deleteDeveloper(developerToDelete);
        return null;
    }

    public String showDeveloperDetails() {
        developerToShow = developers.getRowData();
        return "developerDetails";
    }

    public void uniqueNip(FacesContext context, UIComponent component,
                            Object value) {

        String nip = (String) value;

        for (Developer developer : dm.getAllDevelopers()) {
            if (developer.getNip().equalsIgnoreCase(nip)) {
                FacesMessage message = new FacesMessage(
                        "Developer with this NIP already exists in database");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
        }
    }
}
