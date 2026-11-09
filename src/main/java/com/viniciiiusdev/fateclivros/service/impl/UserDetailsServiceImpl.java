package com.viniciiiusdev.fateclivros.service.impl;

import com.viniciiiusdev.fateclivros.repository.UserRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(@NonNull String registroAluno) throws UsernameNotFoundException {
        return userRepository.findByRegistroAluno(registroAluno).orElseThrow(() -> new UsernameNotFoundException("Aluno não encontrado com o RA: " + registroAluno));
    }
}