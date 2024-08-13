package com.baseapp.it_support_api.service;

import com.baseapp.it_support_api.model.DTO.EquipmentDTO;
import com.baseapp.it_support_api.model.DTO.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getAllUser();
    UserDTO getUserById(Long id);
    UserDTO createUser(UserDTO userDTO);
    UserDTO updateUser(Long id, UserDTO userDTO);
    void deleteUser(Long id);
}
