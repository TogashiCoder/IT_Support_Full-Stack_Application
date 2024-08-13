package com.baseapp.it_support_api.service;

import com.baseapp.it_support_api.exception.TechnicianNotFoundException;
import com.baseapp.it_support_api.model.DTO.TechnicianDTO;
import com.baseapp.it_support_api.model.Entity.Technician;
import com.baseapp.it_support_api.model.Enum.Role;
import com.baseapp.it_support_api.model.mapper.TechnicianMapper;
import com.baseapp.it_support_api.repository.TechnicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnicianServiceImpl implements TechnicianService {

    private final TechnicianRepository technicianRepository;
    private final TechnicianMapper technicianMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<TechnicianDTO> getAllTechnician() {
        List<Technician> technicians = technicianRepository.findAll();
        return technicianMapper.toDTOList(technicians);
    }

    @Override
    public TechnicianDTO getTechnicianById(Long id) {
        Technician technician = technicianRepository.findById(id).orElseThrow(
                () -> new TechnicianNotFoundException("No Technician found with id: " + id));

        return technicianMapper.toDTO(technician);
    }

    @Override
    public TechnicianDTO createTechnician(TechnicianDTO technicianDTO) {
        Technician request = technicianMapper.toEntity(technicianDTO);

        // Check if user already exists. If not, create a new technician.
        if (!technicianRepository.findByUsername(request.getUsername()).isPresent()) {
            Technician technician = new Technician();
            technician.setEmail(request.getEmail());
            technician.setUsername(request.getUsername());
            technician.setPassword(passwordEncoder.encode(request.getPassword()));
            technician.setRole(Role.TECHNICIAN);
            technician = technicianRepository.save(technician);
            return technicianMapper.toDTO(technician);
        } else {
            throw new IllegalArgumentException("Technician with username " + request.getUsername() + " already exists.");
        }
    }

    @Override
    public TechnicianDTO updateTechnician(Long id, TechnicianDTO technicianDTO) {
        Technician existingTechnician = technicianRepository.findById(id).orElseThrow(
                () -> new TechnicianNotFoundException("No Technician found with id: " + id));

        existingTechnician.setEmail(technicianDTO.getEmail());
        existingTechnician.setUsername(technicianDTO.getUsername());
        existingTechnician.setPassword(passwordEncoder.encode(technicianDTO.getPassword()));

        Technician updatedTechnician = technicianRepository.save(existingTechnician);
        return technicianMapper.toDTO(updatedTechnician);
    }

    @Override
    public void deleteTechnician(Long id) {
        Technician technician = technicianRepository.findById(id).orElseThrow(
                () -> new TechnicianNotFoundException("No Technician found with id: " + id));

        technicianRepository.delete(technician);
    }
}
