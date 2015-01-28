package com.example.jeedemo.web;

import com.example.jeedemo.domain.Client;
import com.example.jeedemo.domain.House;
import com.example.jeedemo.service.ClientManager;
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
@Named(value = "clientBean")
public class ClientFormBean implements Serializable {

    private Client client = new Client();
    ListDataModel<Client> clients = new ListDataModel<Client>();

    private Client clientToShow = new Client();
    ListDataModel<House> clientHouses = new ListDataModel<House>();

    @Inject
    ClientManager cm;

    @Inject
    HouseManager hm;

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public ListDataModel<Client> getAllClients() {
        clients.setWrappedData(cm.getAllClients());
        return clients;
    }

    public ListDataModel<House> getClientHouses() {
        clientHouses.setWrappedData(cm.getClientHouses(clientToShow));
        return clientHouses;
    }

    public String addClient() {
        cm.addClient(client);
        return "showClients";
    }

    public String deleteClient() {
        Client clientToDelete = clients.getRowData();
        cm.deleteClient(clientToDelete);
        return null;
    }

    public String showClientDetails() {
        clientToShow = clients.getRowData();
        return "clientDetails";
    }

    public void uniquePesel(FacesContext context, UIComponent component,
                          Object value) {

        String pesel = (String) value;

        for (Client client : cm.getAllClients()) {
            if (client.getPesel().equalsIgnoreCase(pesel)) {
                FacesMessage message = new FacesMessage(
                        "Client with this PESEL already exists in database");
                message.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(message);
            }
        }
    }
}
