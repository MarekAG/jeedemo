package com.example.jeedemo.service;

import com.example.jeedemo.domain.User;

import javax.ejb.EJB;

import javax.ejb.Stateful;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean(name = "userRegister")
@SessionScoped
@Stateful
public class UserRegisterManager implements Serializable     {

    private String username;
    private String password;
    private String type;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @EJB
    private UserManager um;

    @EJB
    private ClientManager cm;

    @EJB
    private DeveloperManager dm;

   @Inject
    private UserLoginManager ulm;

    public String register() {
        if (username.length() > 0 && password.length() > 0) {
            User newUser = new User();
            newUser.setName(username);
            newUser.setPass(password);

            um.addUser(newUser);

            ulm.setUsername(username);
            ulm.setPassword(password);
            ulm.login();

            if("client".equals(type)) {
                return "addClient?faces-redirect=true";
            } else if("developer".equals(type)) {
                return "addDeveloper?faces-redirect=true";
            } else {
                return null;
            }
        }

        return null;
    }

    public String save() {

        return null;
    }
}
