package com.umc.sistema_de_login_seguro.domain.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.umc.sistema_de_login_seguro.domain.model.User;
import com.umc.sistema_de_login_seguro.domain.model.enums.Role;
import com.umc.sistema_de_login_seguro.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getRole() == null) {
            user.setRole(Role.CLIENT);
        }
        return userRepository.save(user);
    }

    public void deleteUserById(String id) {
        userRepository.deleteById(id);
    }
    
    public Optional<User> findById(String id) {
        return userRepository.findById(id);
    }   

    
    public User updateUserById(String id, User user) {
        user = userRepository.findById(id).get();
        if (user.getName() != null) 
            user.setName(user.getName());
        if (user.getEmail() != null) 
            user.setEmail(user.getEmail());
        if (user.getRole() != null) 
            user.setRole(user.getRole());
        if (user.getPassword() != null && !user.getPassword().isBlank()) 
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }   
}