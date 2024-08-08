package com.baseapp.it_support_api.model.DTO;

import com.baseapp.it_support_api.model.Enum.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PersonDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private String username;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Role role;
}
