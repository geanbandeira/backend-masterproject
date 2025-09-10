package com.masterproject.arealogin.controller;

import com.masterproject.arealogin.model.Aula;
import com.masterproject.arealogin.repository.AulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/aulas")
public class AulaController {

    @Autowired
    private AulaRepository aulaRepository;

    /**
     * Endpoint para buscar os detalhes de UMA aula específica pelo seu ID.
     * É provavelmente este endpoint que falta no seu projeto.
     */
    @GetMapping("/{aulaId}")
    public ResponseEntity<Aula> getAulaById(@PathVariable Long aulaId) {
        return aulaRepository.findById(aulaId)
                .map(ResponseEntity::ok) // Se encontrar, retorna 200 OK com a aula
                .orElse(ResponseEntity.notFound().build()); // Se não, retorna 404 Not Found
    }
}