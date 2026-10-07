package com.umc.sistema_de_login_seguro.domain.service;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.umc.sistema_de_login_seguro.domain.model.User;
import com.umc.sistema_de_login_seguro.domain.repository.UserRepository;

import lombok.RequiredArgsConstructor;

/**
 * Serviço que conecta a autenticação do Spring Security ao banco de dados MongoDB.
 */
@Service 
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User usuario = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + email));

        String roleName = usuario.getRole() != null ? usuario.getRole().name() : "CLIENT";

        return new org.springframework.security.core.userdetails.User(
                usuario.getEmail(),
                usuario.getPassword(),
                List.of(new SimpleGrantedAuthority(roleName))
        );
    }
}