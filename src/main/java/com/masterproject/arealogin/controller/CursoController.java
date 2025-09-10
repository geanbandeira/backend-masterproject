package com.masterproject.arealogin.controller;

import com.masterproject.arealogin.model.Aula;
import com.masterproject.arealogin.model.Curso;
import com.masterproject.arealogin.model.Usuario;
import com.masterproject.arealogin.repository.AulaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.masterproject.arealogin.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional; // Importe se necessário


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

    @GetMapping("/catalogo")
    public List<Curso> listarCatalogoDeCursos() {
        return cursoRepository.findAll();
    }

    // NOVO ENDPOINT PARA BUSCAR CURSO PELO SLUG
    @GetMapping("/slug/{cursoSlug}")
    public ResponseEntity<Curso> getCursoBySlug(@PathVariable String cursoSlug) {
        return cursoRepository.findBySlug(cursoSlug)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/meus-cursos")
    public Set<Curso> listarMeusCursos(Principal principal) {
        String username = principal.getName();
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + username));
        return usuario.getCursos();
    }
    
    // ENDPOINT MODIFICADO PARA USAR SLUG
    @GetMapping("/slug/{cursoSlug}/aulas")
    public List<Aula> listarAulasDoCurso(@PathVariable String cursoSlug) {
        return aulaRepository.findByCursoSlug(cursoSlug);
    }
    
    // (Opcional) Você pode manter ou modificar este também
    @GetMapping("/{cursoId}/aulas-gratis")
    public List<Aula> listarAulasGratisDoCurso(@PathVariable Long cursoId) {
        return aulaRepository.findByCursoIdAndGratuitaIsTrue(cursoId);
    }

    @PostMapping("/{cursoId}/matricular")
    public ResponseEntity<?> matricularNoCurso(@PathVariable Long cursoId, Principal principal) {
        Usuario usuario = usuarioRepository.findByUsername(principal.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        usuario.getCursos().add(curso);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Matrícula no curso '" + curso.getTitulo() + "' realizada com sucesso!");
    }

    public interface CursoRepository extends JpaRepository<Curso, Long> {
    Optional<Curso> findBySlug(String slug);
}
}
