package com.viniciiiusdev.fateclivros.service;

import com.viniciiiusdev.fateclivros.dtos.AddUserDTO;
import com.viniciiiusdev.fateclivros.entity.User;

public interface UserService {
    User getUser(Long id);
    void addUser(AddUserDTO userDTO);
}
