package com.umc.sistema_de_login_seguro.domain.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umc.sistema_de_login_seguro.domain.model.User;
import com.umc.sistema_de_login_seguro.domain.model.enums.Role;
import com.umc.sistema_de_login_seguro.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Camada de serviço responsável pelo gerenciamento e regras de negócio de Usuários.
 */
@Service 
@RequiredArgsConstructor
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole(Role.CLIENT);
        }
        return userRepository.save(user);
    }

    @Transactional 
    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }
    
    @Transactional
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }   

    @Transactional 
    public User updateUserById(String id, User updatedUser) {
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (updatedUser.getName() != null) 
            existingUser.setName(updatedUser.getName());
        if (updatedUser.getEmail() != null) 
            existingUser.setEmail(updatedUser.getEmail());
        if (updatedUser.getRole() != null) 
            existingUser.setRole(updatedUser.getRole());
        if (updatedUser.getPassword() != null && !updatedUser.getPassword().isBlank()) 
            existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));

        return userRepository.save(existingUser);
    }
}