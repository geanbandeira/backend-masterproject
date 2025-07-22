package com.masterproject.arealogin.controller;

import com.masterproject.arealogin.model.Aula;
import com.masterproject.arealogin.model.Curso;
import com.masterproject.arealogin.model.Usuario;
import com.masterproject.arealogin.repository.AulaRepository;
import com.masterproject.arealogin.repository.CursoRepository;
import com.masterproject.arealogin.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    // ----- INJEÇÃO ADICIONADA -----
    // Precisamos disso para encontrar o usuário logado
    @Autowired
    private UsuarioRepository usuarioRepository;

    // ----- MÉTODO ANTIGO REMOVIDO E SUBSTITUÍDO POR ESTE -----
    /**
     * Endpoint para listar apenas os cursos em que o usuário logado está matriculado.
     */
    @GetMapping("/meus-cursos")
    public Set<Curso> listarMeusCursos(Principal principal) {
        // Pega o nome de usuário do token JWT do usuário logado
        String username = principal.getName();
        
        // Busca o usuário completo no banco de dados
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado: " + username));

        // Retorna apenas a lista de cursos daquele usuário
        return usuario.getCursos();
    }

    // ----- ESTE MÉTODO CONTINUA IGUAL -----
    /**
     * Endpoint para listar todas as aulas de um curso específico.
     */
    @GetMapping("/{cursoId}/aulas")
    public List<Aula> listarAulasDoCurso(@PathVariable Long cursoId) {
        return aulaRepository.findByCursoId(cursoId);
    }

    // Cole este novo método dentro da classe CursoController
@GetMapping("/{cursoId}/aulas-gratis")
public List<Aula> listarAulasGratisDoCurso(@PathVariable Long cursoId) {
    return aulaRepository.findByCursoIdAndGratuitaIsTrue(cursoId);
}
}