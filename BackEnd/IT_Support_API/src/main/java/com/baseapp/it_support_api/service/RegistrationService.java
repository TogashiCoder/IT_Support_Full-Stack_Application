package com.baseapp.it_support_api.service;

import com.baseapp.it_support_api.model.DTO.AdminDTO;
import com.baseapp.it_support_api.model.DTO.TechnicianDTO;
import com.baseapp.it_support_api.model.DTO.UserDTO;

import java.util.Optional;

public interface RegistrationService {

    Optional<UserDTO> addUser(UserDTO userDto);

    Optional<TechnicianDTO> addTechnician(TechnicianDTO technicianDto);

    Optional<AdminDTO> registerAdmin(AdminDTO adminDTO);


}
