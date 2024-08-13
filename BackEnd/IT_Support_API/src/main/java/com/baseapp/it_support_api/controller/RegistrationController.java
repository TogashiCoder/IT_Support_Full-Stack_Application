package com.baseapp.it_support_api.controller;

import com.baseapp.it_support_api.model.DTO.AdminDTO;
import com.baseapp.it_support_api.model.DTO.TechnicianDTO;
import com.baseapp.it_support_api.model.DTO.UserDTO;
import com.baseapp.it_support_api.model.mapper.AdminMapper;
import com.baseapp.it_support_api.model.mapper.TechnicianMapper;
import com.baseapp.it_support_api.model.mapper.UserMapper;
import com.baseapp.it_support_api.model.security.AuthenticationResponse;
import com.baseapp.it_support_api.service.RegistrationServiceImpl;
import com.baseapp.it_support_api.service.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RegistrationController {

    private final RegistrationServiceImpl registrationService;
    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;
    private final TechnicianMapper technicianMapper;
    private final AdminMapper adminMapper;

//    @PostMapping("/user")
//    public Optional<UserDTO> addCharacter(@RequestBody UserDTO userDTO){
//        return  registrationService.addUser(userDTO);
//    }
    @PostMapping("/user")
    public AuthenticationResponse addCharacter(@RequestBody UserDTO userDTO){
        return  authenticationService.registerUser(userMapper.toEntity(userDTO));
    }

    @PostMapping("/Technician")
    public AuthenticationResponse addTechnician(@RequestBody TechnicianDTO technicianDTO)
    {
        return authenticationService.registerTechnician(technicianMapper.toEntity(technicianDTO));
    }

    @PostMapping("/Admin")
    public AuthenticationResponse RegisterAdmin(@RequestBody AdminDTO adminDTO){
        return authenticationService.registerAdmin(adminMapper.toEntity(adminDTO));
    }



}
