package com.masterproject.arealogin.controller;

import com.masterproject.arealogin.model.Aula;
import com.masterproject.arealogin.model.Curso;
import com.masterproject.arealogin.repository.AulaRepository;
import com.masterproject.arealogin.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AulaRepository aulaRepository;

    // Endpoint para listar todos os cursos
    @GetMapping
    public List<Curso> listarCursos() {
        return cursoRepository.findAll();
    }

    // Endpoint para listar todas as aulas de um curso específico
    @GetMapping("/{cursoId}/aulas")
    public List<Aula> listarAulasDoCurso(@PathVariable Long cursoId) {
        return aulaRepository.findByCursoId(cursoId);
    }
}