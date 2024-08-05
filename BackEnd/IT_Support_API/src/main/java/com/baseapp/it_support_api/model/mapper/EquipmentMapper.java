package com.baseapp.it_support_api.model.mapper;

import com.baseapp.it_support_api.model.DTO.EquipmentDTO;
import com.baseapp.it_support_api.model.Equipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EquipmentMapper {
    @Mappings({
            @Mapping(source = "userId",target = "user.id"),
            @Mapping(source = "technicianId",target = "technician.id"),
    })
    Equipment toEntity(EquipmentDTO DTO);

    @Mappings({
            @Mapping(source = "user.id",target = "userId"),
            @Mapping(source = "technician.id",target = "technicianId"),
    })
    EquipmentDTO toDTO(Equipment entity);

    List<EquipmentDTO> toDTOList(List<Equipment> equipments);
    List<Equipment> toEntityList(List<EquipmentDTO> equipmentDTOS);
}
