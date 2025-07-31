package com.masterproject.arealogin.controller;

import com.masterproject.arealogin.model.Aula;
import com.masterproject.arealogin.model.Curso;
import com.masterproject.arealogin.model.Usuario;
import com.masterproject.arealogin.repository.AulaRepository;
import com.masterproject.arealogin.repository.CursoRepository;
import com.masterproject.arealogin.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    
    private AulaRepository aulaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    // DTO para receber os dados da requisição
    public record MatriculaRequest(String username, Long cursoId) {}

    @PostMapping("/matricular")
    public ResponseEntity<?> matricularAluno(@RequestBody MatriculaRequest request) {
        Usuario usuario = usuarioRepository.findByUsername(request.username())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Curso curso = cursoRepository.findById(request.cursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        usuario.getCursos().add(curso);
        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Usuário " + usuario.getUsername() + " matriculado no curso " + curso.getTitulo());
    }

    // NOVO MÉTODO: Adicionar um novo curso
    @PostMapping("/cursos")
    public ResponseEntity<Curso> adicionarCurso(@RequestBody Curso novoCurso) {
        Curso cursoSalvo = cursoRepository.save(novoCurso);
        return ResponseEntity.ok(cursoSalvo);
    }

    // ... imports e outros métodos ...

    // NOVO MÉTODO: Adicionar uma nova aula a um curso existente
    @PostMapping("/cursos/{cursoId}/aulas")
    public ResponseEntity<Aula> adicionarAula(@PathVariable Long cursoId, @RequestBody Aula novaAula) {
        // Encontra o curso ao qual a aula pertencerá
        Curso curso = cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com id: " + cursoId));

        // Associa a nova aula ao curso
        novaAula.setCurso(curso);

        // Salva a nova aula no banco de dados
        Aula aulaSalva = aulaRepository.save(novaAula);
        return ResponseEntity.ok(aulaSalva);
    }
}