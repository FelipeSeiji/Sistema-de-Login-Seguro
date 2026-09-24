package com.umc.sistema_de_login_seguro.domain.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.umc.sistema_de_login_seguro.domain.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String name);
}
