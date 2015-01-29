package com.example.jeedemo.service;

import com.example.jeedemo.domain.User;
import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

@Stateless
public class UserManager implements Serializable {

    @PersistenceContext
    EntityManager em;

    public void addUser(User user) {
        user.setId(null);
        em.persist(user);
    }

    public void deleteUser(User user) {
        user = em.find(User.class, user);
        em.remove(user);
    }

    public List<User> getAllUsers() {
        return em.createNamedQuery("user.all").getResultList();
    }

    public List<User> getAdminUsers() {
        return em.createNamedQuery("user.admins").getResultList();
    }

    public List<User> getClientUsers() {
        return em.createNamedQuery("user.clients").getResultList();
    }

    public List<User> getDevloperUsers() {
        return em.createNamedQuery("user.developers").getResultList();
    }

    public Boolean isLoginDataOk(String userName, String hashedPass) {
        if ((Integer) em.createNamedQuery("user.exists").setParameter("name", userName).setParameter("pass", hashedPass).getSingleResult() == 1) {
            return true;
        }
        return false;
    }

    public Boolean isUserNameInDb(String userName) {
        if ((Integer)em.createNamedQuery("user.nameExists").setParameter("name", userName).getSingleResult() == 1) {
            return true;
        }
        return false;
    }
    public User getByNameAndPass(String userName, String hashedPass) {
        try {
            return (User) em.createNamedQuery("user.byNameAndPass").setParameter("name", userName).setParameter("pass", hashedPass).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }

        }

}
