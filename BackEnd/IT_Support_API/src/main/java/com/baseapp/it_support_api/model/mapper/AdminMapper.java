package com.baseapp.it_support_api.model.mapper;

import com.baseapp.it_support_api.model.Admin;
import com.baseapp.it_support_api.model.DTO.AdminDTO;
import com.baseapp.it_support_api.model.DTO.UserDTO;
import com.baseapp.it_support_api.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdminMapper {


    Admin toEntity(AdminDTO adminDTO);
    AdminDTO toDTO(Admin admin);
    List<AdminDTO> toDTOList(List<Admin> admins);
    List<Admin> toEntityList(List<AdminDTO> adminDTOS);
}
