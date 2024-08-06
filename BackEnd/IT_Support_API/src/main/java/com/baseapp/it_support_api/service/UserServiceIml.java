package com.baseapp.it_support_api.service;

import com.baseapp.it_support_api.model.DTO.UserDTO;
import com.baseapp.it_support_api.model.User;
import com.baseapp.it_support_api.model.mapper.UserMapper;
import com.baseapp.it_support_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceIml implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public Optional<UserDTO> createNewUser(UserDTO userDTO) {
        if (userDTO != null) {
            User user = userMapper.toEntity(userDTO);
            user = userRepository.save(user);
            return Optional.ofNullable(userMapper.toDTO(user));
        }
        return Optional.empty();
    }

}
