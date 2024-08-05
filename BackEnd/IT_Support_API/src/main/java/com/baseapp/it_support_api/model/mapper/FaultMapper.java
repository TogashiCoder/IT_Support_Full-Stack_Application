package com.baseapp.it_support_api.model.mapper;

import com.baseapp.it_support_api.model.DTO.FaultDTO;
import com.baseapp.it_support_api.model.Fault;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FaultMapper {

    @Mappings({
            @Mapping(source = "equipmentId",target = "equipment.id"),
    })
    Fault toEntity(FaultDTO DTO);

    @Mappings({
            @Mapping(source = "equipment.id",target = "equipmentId"),
    })
    FaultDTO toDTO(Fault entity);

    List<FaultDTO> toDTOList(List<Fault> faults);
    List<Fault> toEntityList(List<FaultDTO> faultDTOS);
}
