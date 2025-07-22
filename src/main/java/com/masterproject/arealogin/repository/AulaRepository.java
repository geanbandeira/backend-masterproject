package com.masterproject.arealogin.repository;

import com.masterproject.arealogin.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AulaRepository extends JpaRepository<Aula, Long> {
    // Método customizado para buscar todas as aulas de um curso específico
    List<Aula> findByCursoId(Long cursoId);
    List<Aula> findByCursoIdAndGratuitaIsTrue(Long cursoId);
}