package com.baseapp.it_support_api.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Technician extends Person {

    @Id
    @GeneratedValue
    private Long id;
    @OneToMany(mappedBy = "technician",cascade = CascadeType.ALL)
    private List<Ticket> tickets;
}
