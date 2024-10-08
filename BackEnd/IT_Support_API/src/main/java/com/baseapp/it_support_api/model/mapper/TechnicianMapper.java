package com.baseapp.it_support_api.model.mapper;

import com.baseapp.it_support_api.model.DTO.TechnicianDTO;
import com.baseapp.it_support_api.model.DTO.UserDTO;
import com.baseapp.it_support_api.model.Entity.Technician;
import com.baseapp.it_support_api.model.Entity.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TechnicianMapper {

    Technician toEntity(TechnicianDTO technicianDTO);
    TechnicianDTO toDTO(Technician technician);
    List<TechnicianDTO> toDTOList(List<Technician> technicians);
    List<Technician> toEntityList(List<TechnicianDTO> technicianDTOS);

}
