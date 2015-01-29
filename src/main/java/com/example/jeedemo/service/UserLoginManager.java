package com.example.jeedemo.service;

import com.example.jeedemo.domain.User;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "userLogin")
@SessionScoped
public class UserLoginManager {

    private String username;
    private String password;
    private User current;

    @EJB
    private UserManager um;

    public String login() {
        if (username.length() > 0 && password.length() > 0) {

            String hashedPass = null;
//            try {
//                MessageDigest md = MessageDigest.getInstance("SHA-256");
//                md.update(password.getBytes());
//
//                byte[] bytes = md.digest();
//
//                StringBuilder temp = new StringBuilder();
//
//                for (int i = 0; i < bytes.length; i++) {
//                    temp.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
//                }
//
//                hashedPass = temp.toString();
//
//            } catch (NoSuchAlgorithmException e) {
//                e.printStackTrace();
//            }
//
//            current = um.getByNameAndPass(username, hashedPass);


            current = um.getByNameAndPass(username, password);
        }

        if (current == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Unknown user name, try again"));
            return (username = password = null);
        } else {
            return "addClient?faces-redirect=true";
        }
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

    public boolean isLoggedIn() {
        return current != null;
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

    public User getCurrent() {
        return current;
    }

}
