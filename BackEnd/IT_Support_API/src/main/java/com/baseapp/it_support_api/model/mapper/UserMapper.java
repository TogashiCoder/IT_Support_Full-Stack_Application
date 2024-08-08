package com.baseapp.it_support_api.model.mapper;

import com.baseapp.it_support_api.model.DTO.UserDTO;
import org.mapstruct.Mapper;
import com.baseapp.it_support_api.model.Entity.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(UserDTO userDTO);
    UserDTO toDTO(User user);
    List<UserDTO> toDTOList(List<User> users);
    List<User> toEntityList(List<UserDTO> userDTOs);

}
