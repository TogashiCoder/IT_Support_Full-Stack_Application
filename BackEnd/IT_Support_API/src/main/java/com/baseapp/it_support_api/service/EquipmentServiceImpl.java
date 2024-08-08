package com.baseapp.it_support_api.service;

import com.baseapp.it_support_api.exception.EquipmentNotFoundException;
import com.baseapp.it_support_api.model.DTO.EquipmentDTO;
import com.baseapp.it_support_api.model.Entity.Equipment;
import com.baseapp.it_support_api.model.mapper.EquipmentMapper;
import com.baseapp.it_support_api.repository.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipmentServiceImpl implements EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private EquipmentMapper equipmentMapper;

    public List<EquipmentDTO> getAllEquipment() {
        List<Equipment> equipmentList = equipmentRepository.findAll();
        return equipmentMapper.toDTOList(equipmentList);
    }

    public EquipmentDTO getEquipmentById(Long id) {
        Optional<Equipment> equipment = equipmentRepository.findById(id);
        if (equipment.isPresent()) {
            return equipmentMapper.toDTO(equipment.get());
        } else {
            throw new EquipmentNotFoundException("Equipment with id " + id + " not found");
        }
    }

    public EquipmentDTO createEquipment(EquipmentDTO equipmentDTO) {
        Equipment equipment = equipmentMapper.toEntity(equipmentDTO);
        Equipment savedEquipment = equipmentRepository.save(equipment);
        return equipmentMapper.toDTO(savedEquipment);
    }

    public EquipmentDTO updateEquipment(Long id, EquipmentDTO equipmentDTO) {
        Optional<Equipment> optionalEquipment = equipmentRepository.findById(id);
        if (optionalEquipment.isPresent()) {
            Equipment equipment = optionalEquipment.get();
            equipment.setImageUrl(equipmentDTO.getImageUrl());
            equipment.setEquipmentName(equipmentDTO.getEquipmentName());
            equipment.setPurchaseDate(equipmentDTO.getPurchaseDate());
            equipment.setAssetValue(equipmentDTO.getAssetValue());
            equipment.setSerialNumber(equipmentDTO.getSerialNumber());
            Equipment updatedEquipment = equipmentRepository.save(equipment);
            return equipmentMapper.toDTO(updatedEquipment);
        } else {
            throw new EquipmentNotFoundException("Equipment with id " + id + " not found");
        }
    }

    public void deleteEquipment(Long id) {
        Optional<Equipment> optionalEquipment = equipmentRepository.findById(id);
        if (optionalEquipment.isPresent()) {
            equipmentRepository.delete(optionalEquipment.get());
        } else {
            throw new EquipmentNotFoundException("Equipment with id " + id + " not found");
        }
    }
}
