package com.mustafa.ToDo.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.mustafa.ToDo.DTO.UserCreateRequestDTO;
import com.mustafa.ToDo.Entity.User;
import com.mustafa.ToDo.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository ;

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(UserCreateRequestDTO userCreateRequestDTO){
        User user = new User();
        user.setUsername(userCreateRequestDTO.getUsername());
        user.setPassword(BCrypt.withDefaults().hashToString(12, userCreateRequestDTO.getPassword().toCharArray()));
        user.setName(userCreateRequestDTO.getName());
        user.setLastname(userCreateRequestDTO.getLastname());
        userRepository.save(user);
        return user;
    }
    @Override
    public Optional<User> findUsername(String username) {
        return userRepository.findByUsername(username);

    }
}
