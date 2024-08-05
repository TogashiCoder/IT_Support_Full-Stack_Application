package com.baseapp.it_support_api.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class User extends Person {
    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Equipment> equipments;
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
