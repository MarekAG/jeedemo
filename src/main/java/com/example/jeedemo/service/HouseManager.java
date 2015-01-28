package com.example.jeedemo.service;

import com.example.jeedemo.domain.Client;
import com.example.jeedemo.domain.Developer;
import com.example.jeedemo.domain.House;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Stateless
public class HouseManager implements Serializable {

    @PersistenceContext
    EntityManager em;

    public void addHouse(House house) {
        house.setId(null);
        em.persist(house);
    }

    public void deleteHouse(House house) {
        house = em.find(House.class, house.getId());
        em.remove(house);
    }

    public List<House> getAllHouses() {
        return em.createNamedQuery("house.all").getResultList();
    }

    public List<House> getUnsoldHouses() {
        return em.createNamedQuery("house.unsold").getResultList();
    }

    public List<House> searchHousesByDate(Date date) {
        return em.createNamedQuery("house.when").setParameter("date", date).getResultList();
    }

    public void sellHouse(Long clientId, Long houseId) {
        if(clientId != null && houseId != null) {
            Client client = em.find(Client.class, clientId);
            House house = em.find(House.class, houseId);
            try {
                Developer developer = (Developer) em.createNamedQuery("developer.house").setParameter("houseId", house.getId()).getSingleResult();
                developer.getHouses().remove(house);
            } catch (javax.persistence.NoResultException e) {

            }
            house.setSold(true);
            client.getHouses().add(house);
        }
    }
}
