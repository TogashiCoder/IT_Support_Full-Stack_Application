package com.baseapp.it_support_api.service;


import com.baseapp.it_support_api.model.DTO.UserDTO;

import java.util.Optional;

/**
 * UserService interface that provides business logic for User-related operations.
 */
public interface UserService {

    /**
     * Creates a new user in the system based on the provided UserDTO.
     * This method handles all aspects of user creation including validation, persistence, and any business rules associated with new user entries.
     *
     * @param userDTO The UserDTO object containing the data of the new user to be created.
     * @return An Optional containing the UserDTO of the newly created user if the creation is successful and the user is valid according to business rules,
     *         or an empty Optional if the user cannot be created due to validation failures or other business rules.
     */
    Optional<UserDTO> createNewUser(UserDTO userDTO);
}
