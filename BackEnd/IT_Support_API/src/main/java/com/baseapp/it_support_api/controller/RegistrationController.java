package com.baseapp.it_support_api.controller;

import com.baseapp.it_support_api.model.DTO.RegistrationDTO;
import com.baseapp.it_support_api.model.Person;
import com.baseapp.it_support_api.service.RegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;

    @PostMapping
    public ResponseEntity<Person> registerUser(@RequestBody RegistrationDTO registrationDto) {
        Person registeredPerson = registrationService.registerUser(registrationDto);
        return ResponseEntity.ok(registeredPerson);
    }
}
