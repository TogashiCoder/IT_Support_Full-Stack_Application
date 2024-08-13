package com.baseapp.it_support_api.service;

import com.baseapp.it_support_api.model.DTO.EquipmentDTO;
import com.baseapp.it_support_api.model.DTO.TechnicianDTO;

import java.util.List;

public interface TechnicianService {
    List<TechnicianDTO> getAllTechnician();
    TechnicianDTO getTechnicianById(Long id);
    TechnicianDTO createTechnician(TechnicianDTO technicianDTO);
    TechnicianDTO updateTechnician(Long id, TechnicianDTO technicianDTO);
    void deleteTechnician(Long id);
}
