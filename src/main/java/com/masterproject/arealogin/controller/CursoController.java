package com.masterproject.arealogin.controller;

import com.masterproject.arealogin.model.Aula;
import com.masterproject.arealogin.model.Curso;
import com.masterproject.arealogin.model.Usuario;
import com.masterproject.arealogin.repository.AulaRepository;
import com.masterproject.arealogin.repository.CursoRepository;
import com.masterproject.arealogin.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // IMPORT ADICIONADO PARA CORRIGIR O ERRO
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private AulaRepository aulaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Endpoint para listar TODOS os cursos disponíveis no catálogo.
     */
    @GetMapping("/catalogo")
    public List<Curso> listarCatalogoDeCursos() {
        return cursoRepository.findAll();
    }

    /**
     * Endpoint para listar apenas os cursos em que o usuário logado está matriculado.
     */
    @GetMapping("/meus-cursos")
    public Set<Curso> listarMeusCursos(Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + username));
        return usuario.getCursos();
    }

    /**
     * Endpoint para listar todas as aulas de um curso específico (para alunos matriculados).
     */
    @GetMapping("/{cursoId}/aulas")
    public List<Aula> listarAulasDoCurso(@PathVariable Long cursoId) {
        return aulaRepository.findByCursoId(cursoId);
    }

    /**
     * Endpoint para listar apenas as aulas gratuitas de um curso.
     */
    @GetMapping("/{cursoId}/aulas-gratis")
    public List<Aula> listarAulasGratisDoCurso(@PathVariable Long cursoId) {
        return aulaRepository.findByCursoIdAndGratuitaIsTrue(cursoId);
    }

    /**
     * Endpoint para um usuário logado se matricular em um curso.
     */
    @PostMapping("/{cursoId}/matricular")
    public ResponseEntity<?> matricularNoCurso(@PathVariable Long cursoId, Principal principal) {
        // Encontra o usuário logado
        Usuario usuario = usuarioRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        // Encontra o curso pelo ID
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        // Adiciona o curso à lista do usuário e salva
        usuario.getCursos().add(curso);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Matrícula no curso '" + curso.getTitulo() + "' realizada com sucesso!");
    }
}
