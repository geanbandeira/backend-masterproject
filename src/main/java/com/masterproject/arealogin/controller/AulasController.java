package com.masterproject.arealogin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/aulas")
public class AulasController {

    // Este é um endpoint protegido. Só pode ser acessado com um token JWT válido.
    @GetMapping
    public List<Map<String, String>> getAulas() {
        // No mundo real, você buscaria isso do banco de dados
        return List.of(
            Map.of("titulo", "Aula 1: Introdução à API", "conteudo", "Nesta aula veremos como..."),
            Map.of("titulo", "Aula 2: Segurança com JWT", "conteudo", "JWT é um padrão para...")
        );
    }
}