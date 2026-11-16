package com.viniciiiusdev.fateclivros.mapper;

import com.viniciiiusdev.fateclivros.dtos.BookStats;
import com.viniciiiusdev.fateclivros.dtos.UserProfileDTO;
import com.viniciiiusdev.fateclivros.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserProfileDTO mapUserToUserProfile(User user, BookStats metricas) {
        return new UserProfileDTO(
                user.getUserId(),
                user.getName(),
                user.getRegistroAluno(),
                user.getAcademicEmail(),
                metricas
        );

    }
}
