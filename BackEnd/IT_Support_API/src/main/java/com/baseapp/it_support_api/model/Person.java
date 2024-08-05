package com.baseapp.it_support_api.model;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Person {
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    @Embedded
    private Adresse adresse;
}
