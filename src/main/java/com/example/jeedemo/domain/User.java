package com.example.jeedemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "user.all", query = "Select u from User u"),
        @NamedQuery(name = "user.exists", query = "select count (u) from User u where u.name = :name and u.pass = :pass"),
        @NamedQuery(name = "user.byNameAndPass", query = "select u from User u where u.name = :name and u.pass = :pass"),
        @NamedQuery(name = "user.nameExists", query = "select count (u) from User u where u.name = :name"),
        @NamedQuery(name = "user.admins", query = "select u from User u where u.isAdmin = true order by u.name"),
        @NamedQuery(name = "user.clients", query = "select u from User u where u.isClient = true order by u.name"),
        @NamedQuery(name = "user.developers", query = "select u from User u where u.isClient = true order by u.name"),
        @NamedQuery(name = "users.withoutRoles", query = "select u from User u where u.isAdmin = false and u.isClient =false and u.isDeveloper = false order by u.name")
        })
public class User {

    private Long id;
    private String name = "";
    private String pass = "";
    private Boolean isAdmin = false;
    private Boolean isClient = false;
    private Boolean isDeveloper = false;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Column(unique = true, updatable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Boolean getIsClient() {
        return isClient;
    }

    public void setIsClient(Boolean isClient) {
        this.isClient = isClient;
    }

    public Boolean getIsDeveloper() {
        return isDeveloper;
    }

    public void setIsDeveloper(Boolean isDeveloper) {
        this.isDeveloper = isDeveloper;
    }
}
