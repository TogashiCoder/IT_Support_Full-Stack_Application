package com.baseapp.it_support_api.service.security;

import com.baseapp.it_support_api.exception.PersonNotFoundException;
import com.baseapp.it_support_api.model.Entity.*;
import com.baseapp.it_support_api.model.Enum.Role;
import com.baseapp.it_support_api.model.security.AuthenticationResponse;
import com.baseapp.it_support_api.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private  final TechnicianRepository technicianRepository;
    private  final AdminRepository adminRepository;
    private final PersonRepository personRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;



    public AuthenticationResponse registerUser(User request) {

        // check if user already exist. if exist than authenticate the user
        if(userRepository.findByUsername(request.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, "User already exist",request.getId());
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        user = userRepository.save(user);


        String jwt = jwtService.generateToken(user);
        return new AuthenticationResponse(jwt, "User registration was successful",user.getId());
    }

    public AuthenticationResponse registerAdmin(Admin request) {



        Admin admin = new Admin();
        admin.setEmail(request.getEmail());
        admin.setUsername(request.getUsername());
        admin.setPassword(passwordEncoder.encode(request.getPassword()));
        admin.setRole(Role.ADMIN);
        admin = adminRepository.save(admin);


        String jwt = jwtService.generateToken(admin);
        return new AuthenticationResponse(jwt, "User registration was successful",admin.getId());
    }

    public AuthenticationResponse registerTechnician(Technician request) {

        // check if user already exist. if exist than authenticate the user
        if(technicianRepository.findByUsername(request.getUsername()).isPresent()) {
            return new AuthenticationResponse(null, "Admin already exist",request.getId());
        }

        Technician technician = new Technician();
        technician.setEmail(request.getEmail());
        technician.setUsername(request.getUsername());
        technician.setPassword(passwordEncoder.encode(request.getPassword()));
        technician.setRole(Role.ADMIN);
        technician = technicianRepository.save(technician);


        String jwt = jwtService.generateToken(technician);
        return new AuthenticationResponse(jwt, "User registration was successful",technician.getId());
    }







    public AuthenticationResponse authenticate(Person request) {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            Person character = personRepository.findByUsername(request.getUsername()).orElseThrow(()->(new PersonNotFoundException("no character found with "+request.getUsername()+" username")));
            String jwt = jwtService.generateToken(character);

            return new AuthenticationResponse(jwt,"login was successful",character.getId());
    }

}
