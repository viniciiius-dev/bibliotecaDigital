
package com.viniciiiusdev.fateclivros.service.impl;

import com.viniciiiusdev.fateclivros.Exception.ResourceNotFoundException;
import com.viniciiiusdev.fateclivros.Exception.UnauthorizedException;
import com.viniciiiusdev.fateclivros.entity.User;
import com.viniciiiusdev.fateclivros.repository.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserService {

    private final UserRepository userRepository;

    public AuthenticatedUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Authentication getAuthentication() {
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null ||
                 !authentication.isAuthenticated() ||
                authentication instanceof AnonymousAuthenticationToken) {
            throw new UnauthorizedException(
                    "Não foi possível encontrar usuário autenticado"
            );
        }

        return authentication;
    }

    public User getAuthenticatedUser() {
        String registroAluno = getAuthentication().getName();

        return userRepository.findByRegistroAluno(registroAluno)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuário não encontrado.")
                );
    }
}
