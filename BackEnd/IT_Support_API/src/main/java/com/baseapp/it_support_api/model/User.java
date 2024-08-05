package com.baseapp.it_support_api.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class User extends Person {

    @Id
    @GeneratedValue
    private Long id;

}
