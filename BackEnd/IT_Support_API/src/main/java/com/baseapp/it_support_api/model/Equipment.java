package com.baseapp.it_support_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Equipment {
    @Id
    @GeneratedValue
    private Long id;
    private String imageUrl;
    private String equipmentName;
    private LocalDate purchaseDate;
    private String assetValue;
    private String serialNumber;
}
