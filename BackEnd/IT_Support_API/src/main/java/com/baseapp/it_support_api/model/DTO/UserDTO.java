package com.baseapp.it_support_api.model.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class UserDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private AdresseDTO adresse;
    @JsonIgnore
    private List<EquipmentDTO> equipments;
    @JsonIgnore
    private List<TicketDTO> tickets;
}
