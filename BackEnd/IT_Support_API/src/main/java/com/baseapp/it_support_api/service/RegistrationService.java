package com.baseapp.it_support_api.service;

import com.baseapp.it_support_api.model.Admin;
import com.baseapp.it_support_api.model.DTO.RegistrationDTO;
import com.baseapp.it_support_api.model.Person;
import com.baseapp.it_support_api.model.Technician;
import com.baseapp.it_support_api.model.User;
import com.baseapp.it_support_api.repository.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RegistrationService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;

    public Person registerUser(RegistrationDTO registrationDto) {

        Person person;

        switch (registrationDto.getRole()) {
            case TECHNICIAN:
                person = Technician.builder()
                        .username(registrationDto.getUsername())
                        .email(registrationDto.getEmail())
                        .password(passwordEncoder.encode(registrationDto.getPassword()))
                        .role(registrationDto.getRole())
                        .build();
                break;
            case ADMIN:
                person = Admin.builder()
                        .username(registrationDto.getUsername())
                        .email(registrationDto.getEmail())
                        .password(passwordEncoder.encode(registrationDto.getPassword()))
                        .role(registrationDto.getRole())
                        .build();
                break;
            case USER:
            default:
                person = User.builder()
                        .username(registrationDto.getUsername())
                        .email(registrationDto.getEmail())
                        .password(passwordEncoder.encode(registrationDto.getPassword()))
                        .role(registrationDto.getRole())
                        .build();
                break;
        }

        return personRepository.save(person);
    }

}

