package com.example.jeedemo.service;

import com.example.jeedemo.domain.Admin;
import com.example.jeedemo.domain.House;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class AdminManager implements Serializable {

    @PersistenceContext
    EntityManager em;

    public void addAdmin(Admin admin) {
        admin.setId(null);
        em.persist(admin);
    }

    public void deleteAdmin(Admin admin) {
        admin = em.find(Admin.class, admin.getId());
        em.remove(admin);
    }

    public List<Admin> getAllAdmins() {
        return em.createNamedQuery("admin.all").getResultList();
    }

}

