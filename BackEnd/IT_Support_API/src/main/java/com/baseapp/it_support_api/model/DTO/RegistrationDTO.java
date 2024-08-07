package com.baseapp.it_support_api.model.DTO;

import com.baseapp.it_support_api.model.Enum.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDTO {
    private String username;
    private String email;
    private String password;
    private Role role;
}
