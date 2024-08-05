package com.baseapp.it_support_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admins")
public class Admin extends Person {

    @Id
    @GeneratedValue
    private Long id;
}
