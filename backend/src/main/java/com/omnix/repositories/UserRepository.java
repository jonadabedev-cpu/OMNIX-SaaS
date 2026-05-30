package com.omnix.repositories;

import com.omnix.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Busca usuário pelo email — usado no login e no UserDetailsServiceImpl
    Optional<User> findByEmail(String email);

    // Verifica se email já está cadastrado — usado no registro
    boolean existsByEmail(String email);
}
