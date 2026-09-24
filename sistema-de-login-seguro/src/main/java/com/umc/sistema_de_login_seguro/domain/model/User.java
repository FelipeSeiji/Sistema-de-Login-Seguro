package com.umc.sistema_de_login_seguro.domain.model;

import org.springframework.data.annotation.Id;

import com.umc.sistema_de_login_seguro.domain.model.enums.Role;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String id;

    private String name;

    @Email
    private String email;

    private String password;

    private Role role;
}
