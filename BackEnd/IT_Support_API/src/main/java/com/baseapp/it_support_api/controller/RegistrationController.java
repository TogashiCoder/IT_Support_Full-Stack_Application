package com.baseapp.it_support_api.controller;

import com.baseapp.it_support_api.model.DTO.AdminDTO;
import com.baseapp.it_support_api.model.DTO.TechnicianDTO;
import com.baseapp.it_support_api.model.DTO.UserDTO;
import com.baseapp.it_support_api.service.RegistrationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    @Autowired
    RegistrationServiceImpl registrationService;

    @PostMapping("/user")
    public Optional<UserDTO> addCharacter(@RequestBody UserDTO userDTO){
        return  registrationService.addUser(userDTO);
    }

    @PostMapping("/Technician")
    public Optional<TechnicianDTO> addTechnician(@RequestBody TechnicianDTO technicianDTO)
    {
        return registrationService.addTechnician(technicianDTO);
    }

    @PostMapping("/Admin")
    public Optional<?> RegisterAdmin(@RequestBody AdminDTO adminDTO){
        return registrationService.registerAdmin(adminDTO);
    }



}
