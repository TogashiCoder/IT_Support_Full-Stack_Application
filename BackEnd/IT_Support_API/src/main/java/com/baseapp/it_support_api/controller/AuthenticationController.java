package com.baseapp.it_support_api.controller;

import com.baseapp.it_support_api.model.DTO.AdminDTO;
import com.baseapp.it_support_api.model.DTO.PersonDTO;
import com.baseapp.it_support_api.model.mapper.PersonMapper;
import com.baseapp.it_support_api.model.security.AuthenticationResponse;
import com.baseapp.it_support_api.service.RegistrationServiceImpl;
import com.baseapp.it_support_api.service.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final PersonMapper personMapper;


    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody PersonDTO personDTO) {
        return ResponseEntity.ok(authenticationService.authenticate(personMapper.toEntity(personDTO)));
    }



}
