package com.baseapp.it_support_api.model.mapper;

import com.baseapp.it_support_api.model.DTO.AdminDTO;
import com.baseapp.it_support_api.model.DTO.EquipmentDTO;
import com.baseapp.it_support_api.model.Entity.Admin;
import com.baseapp.it_support_api.model.Entity.Equipment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {

    Equipment toEntity(EquipmentDTO equipmentDTO);
    EquipmentDTO toDTO(Equipment equipment);
    List<EquipmentDTO> toDTOList(List<Equipment> equipments);
    List<Equipment> toEntityList(List<EquipmentDTO> equipmentDTOS);
}
