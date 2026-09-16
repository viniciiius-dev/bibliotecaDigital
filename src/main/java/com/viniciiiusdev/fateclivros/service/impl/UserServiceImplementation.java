package com.viniciiiusdev.fateclivros.service.impl;

import com.viniciiiusdev.fateclivros.repository.UserRepository;
import com.viniciiiusdev.fateclivros.dtos.AddUserDTO;
import com.viniciiiusdev.fateclivros.entity.User;
import com.viniciiiusdev.fateclivros.service.UserService;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    public UserServiceImplementation(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    public User getUser(Long id){
        return userRepository.getReferenceById(id);
    }

    public void addUser(AddUserDTO addUserDTO){
        User newUser = new User(addUserDTO.name(), addUserDTO.registroAluno());
        userRepository.saveAndFlush(newUser);
    }
}

