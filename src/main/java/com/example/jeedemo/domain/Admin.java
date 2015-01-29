package com.example.jeedemo.domain;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = "admin.all", query = "Select a from Admin a"),
})
public class Admin {


    private Long id;

    private User user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
