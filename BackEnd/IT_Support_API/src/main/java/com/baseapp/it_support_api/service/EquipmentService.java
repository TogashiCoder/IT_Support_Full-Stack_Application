package com.baseapp.it_support_api.service;

import com.baseapp.it_support_api.model.DTO.EquipmentDTO;

import java.util.List;

public interface EquipmentService {

    List<EquipmentDTO> getAllEquipment();
    EquipmentDTO getEquipmentById(Long id);
    EquipmentDTO createEquipment(EquipmentDTO equipmentDTO);
    EquipmentDTO updateEquipment(Long id, EquipmentDTO equipmentDTO);
    void deleteEquipment(Long id);

}
