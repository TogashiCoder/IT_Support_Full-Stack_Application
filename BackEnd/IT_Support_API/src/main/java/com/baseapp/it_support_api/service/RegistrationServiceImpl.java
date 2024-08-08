package com.baseapp.it_support_api.service;

import com.baseapp.it_support_api.model.DTO.AdminDTO;
import com.baseapp.it_support_api.model.DTO.TechnicianDTO;
import com.baseapp.it_support_api.model.DTO.UserDTO;
import com.baseapp.it_support_api.model.Entity.Admin;
import com.baseapp.it_support_api.model.Entity.Technician;
import com.baseapp.it_support_api.model.Entity.User;
import com.baseapp.it_support_api.model.mapper.AdminMapper;
import com.baseapp.it_support_api.model.mapper.TechnicianMapper;
import com.baseapp.it_support_api.model.mapper.UserMapper;
import com.baseapp.it_support_api.repository.AdminRepository;
import com.baseapp.it_support_api.repository.PersonRepository;
import com.baseapp.it_support_api.repository.TechnicianRepository;
import com.baseapp.it_support_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService{

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TechnicianRepository technicianRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TechnicianMapper technicianMapper;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private AdminMapper adminMapper;


    @Override
    public Optional<UserDTO> addUser(UserDTO userDto) {
        User user = userMapper.toEntity(userDto);
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user = userRepository.save(user);
        return Optional.of(userMapper.toDTO(user));
    }

    @Override
    public Optional<TechnicianDTO> addTechnician(TechnicianDTO technicianDto) {
        Technician technician = technicianMapper.toEntity(technicianDto);
        technician.setPassword(passwordEncoder.encode(technicianDto.getPassword()));
        technician = technicianRepository.save(technician);
        return Optional.of(technicianMapper.toDTO(technician));
    }

    @Override
    public Optional<AdminDTO>registerAdmin(AdminDTO adminDTO){
        Admin admin = adminMapper.toEntity(adminDTO);
        admin.setPassword(passwordEncoder.encode(adminDTO.getPassword()));
        admin = adminRepository.save(admin);
        return Optional.of(adminMapper.toDTO(admin));
    }



}

