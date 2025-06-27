package com.masterproject.arealogin.repository;

import com.masterproject.arealogin.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    // Método para buscar um usuário pelo nome de usuário (username)
    Optional<Usuario> findByUsername(String username);
}