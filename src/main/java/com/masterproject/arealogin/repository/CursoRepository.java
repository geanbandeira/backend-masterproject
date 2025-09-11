package com.masterproject.arealogin.repository;

import com.masterproject.arealogin.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    
    Optional<Curso> findBySlug(String slug);

    // ADICIONE ESTE MÉTODO PARA CORRIGIR O ERRO
    Optional<Curso> findByTitulo(String titulo);
}