package com.baseapp.it_support_api.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Adresse {
    private String streetName;
    private String city;
    private String state;
    private String postalCode;
    private String country;

}
