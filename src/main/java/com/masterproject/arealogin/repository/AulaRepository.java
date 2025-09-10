package com.masterproject.arealogin.repository;

import com.masterproject.arealogin.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List; // Importe se necessário

public interface AulaRepository extends JpaRepository<Aula, Long> {
    
    List<Aula> findByCursoId(Long cursoId);
    List<Aula> findByCursoIdAndGratuitaIsTrue(Long cursoId);

    // NOVO MÉTODO
    List<Aula> findByCursoSlug(String cursoSlug);
}