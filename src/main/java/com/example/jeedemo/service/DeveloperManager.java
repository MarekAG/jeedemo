package com.example.jeedemo.service;

import com.example.jeedemo.domain.Developer;
import com.example.jeedemo.domain.House;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class DeveloperManager implements Serializable {

    @PersistenceContext
    EntityManager em;

    public void addDeveloper(Developer developer) {
        developer.setId(null);
        em.persist(developer);
    }

    public void deleteDeveloper(Developer developer) {
        developer = em.find(Developer.class, developer.getId());
        em.remove(developer);
    }

    public void addHouse(Long id, House house) {
        if(id != null) {
            house = em.find(House.class, house.getId());
            Developer developer = em.find(Developer.class, id);
            List<House> houses = new ArrayList<House>(developer.getHouses());
            developer.getHouses().add(house);
        }
    }

    public void removeHouse(House house) {
            house = em.find(House.class, house.getId());
            List<Developer> developers = getAllDevelopers();
            for(Developer d : developers) {
                if(d.getHouses().contains(house)) {
                    d.getHouses().remove(house);
                }
            }
    }

    public List<Developer> getAllDevelopers() {
        return em.createNamedQuery("developer.all").getResultList();
    }

//    public List<Developer> searchDevelopersByLastName(String lastName) {
//        return em.createNamedQuery("developer.like").setParameter("lastName", lastName).getResultList();
//    }

    public List<House> getDeveloperHouses(Developer developer) {
        developer = em.find(Developer.class, developer.getId());
        List<House> houses = new ArrayList<House>(developer.getHouses());
        return houses;
    }

//    public List<House> getDeveloperHouses(Long id) {
//        Developer developer = em.find(Developer.class, id);
//        List<House> houses = new ArrayList<House>(developer.getHouses());
//        return houses;
//    }
}
