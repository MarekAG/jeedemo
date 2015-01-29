package com.example.jeedemo.service;

import com.example.jeedemo.domain.Client;
import com.example.jeedemo.domain.House;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class ClientManager implements Serializable {

    @PersistenceContext
    EntityManager em;

    public void addClient(Client client) {
        client.setId(null);
        em.persist(client);
    }

    public void deleteClient(Client client) {
        client = em.find(Client.class, client.getId());
        em.remove(client);
    }

    public void removeHouse(House house) {
        house = em.find(House.class, house.getId());
        List<Client> clients = getAllClients();
        for(Client c : clients) {
            if(c.getHouses().contains(house)) {
                c.getHouses().remove(house);
            }
        }
    }

    public List<Client> getAllClients() {
        return em.createNamedQuery("client.all").getResultList();
    }

    public List<Client> searchClientsByLastName(String lastName) {
        return em.createNamedQuery("client.like").setParameter("lastName", lastName).getResultList();
    }

    public List<House> getClientHouses(Client client) {
        client = em.find(Client.class, client.getId());
        List<House> houses = new ArrayList<House>(client.getHouses());
        return houses;
    }
}
