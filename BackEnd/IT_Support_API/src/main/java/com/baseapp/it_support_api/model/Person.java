package com.baseapp.it_support_api.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Person {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String password;
    @Embedded
    private Adresse adresse;
}
