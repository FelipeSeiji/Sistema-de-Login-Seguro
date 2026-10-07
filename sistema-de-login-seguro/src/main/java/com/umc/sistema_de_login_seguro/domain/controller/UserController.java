package com.umc.sistema_de_login_seguro.domain.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.umc.sistema_de_login_seguro.domain.model.User;
import com.umc.sistema_de_login_seguro.domain.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController 
@RequestMapping("/api/users")
@RequiredArgsConstructor 
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "Cria um novo usuário", description = "Cria um novo usuário")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User created = userService.createUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao criar usuário: " + e.getMessage());
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta um usuário", description = "Deleta um usuário")
    public ResponseEntity<String> deleteUserById(@PathVariable String id) {
        try {
            userService.deleteUserById(id);
            return ResponseEntity.ok("Usuário deletado com sucesso!");
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao deletar usuário: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Atualiza um usuário", description = "Atualiza um usuário")
    public ResponseEntity<?> updateUserById(@PathVariable String id, @RequestBody User user) {
        try {
            User updated = userService.updateUserById(id, user);
            return ResponseEntity.ok(updated);
        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao atualizar usuário: " + e.getMessage());
        }
    }
    
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    @Operation(summary = "Busca um usuário pelo ID", description = "Busca um usuário pelo ID")
    public ResponseEntity<?> findById(@PathVariable String id) {
        return userService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}