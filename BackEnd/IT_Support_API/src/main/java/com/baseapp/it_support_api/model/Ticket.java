package com.baseapp.it_support_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDate creationDate;
    private String description;
    private String status;
}
