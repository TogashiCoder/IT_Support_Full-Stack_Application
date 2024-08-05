package com.baseapp.it_support_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Technician extends Person {

    @Id
    @GeneratedValue
    private Long id;
}
