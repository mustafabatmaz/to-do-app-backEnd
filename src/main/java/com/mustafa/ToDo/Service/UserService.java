package com.mustafa.ToDo.Service;

import com.mustafa.ToDo.DTO.UserCreateRequestDTO;
import com.mustafa.ToDo.Entity.User;

import java.util.Optional;

public interface UserService {
    User addUser(UserCreateRequestDTO userCreateRequestDTO);
    Optional<User> findUsername(String username);
}
