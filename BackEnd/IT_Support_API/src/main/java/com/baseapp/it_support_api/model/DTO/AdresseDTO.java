package com.baseapp.it_support_api.model.DTO;

import lombok.Data;

@Data
public class AdresseDTO {
    private String streetName;
    private String city;
    private String state;
    private String postalCode;
    private String country;
}
