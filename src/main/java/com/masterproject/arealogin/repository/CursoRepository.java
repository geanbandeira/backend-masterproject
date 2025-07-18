package com.masterproject.arealogin.repository;

import com.masterproject.arealogin.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
}