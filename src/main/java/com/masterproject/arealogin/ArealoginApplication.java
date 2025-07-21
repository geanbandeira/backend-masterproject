package com.masterproject.arealogin;

import com.masterproject.arealogin.model.Curso;
import com.masterproject.arealogin.model.Usuario;
import com.masterproject.arealogin.repository.CursoRepository;
import com.masterproject.arealogin.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class ArealoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArealoginApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(UsuarioRepository usuarioRepository, CursoRepository cursoRepository,
            PasswordEncoder passwordEncoder) {
        return args -> {
            // --- CRIAÇÃO DOS CURSOS ---
            // Só cria se não houver nenhum curso no banco
            if (cursoRepository.count() == 0) {
                Curso powerBi = new Curso();
                powerBi.setTitulo("Power BI");
                powerBi.setDescricao("Domine a ferramenta de Business Intelligence mais poderosa do mercado.");

                Curso excelPro = new Curso();
                excelPro.setTitulo("Excel Pro");
                excelPro.setDescricao("Do básico ao avançado, torne-se um mestre em planilhas.");

                Curso gestaoProjetos = new Curso();
                gestaoProjetos.setTitulo("Gestão de Projetos Híbrida");
                gestaoProjetos.setDescricao("Aprenda a gerenciar projetos com metodologias ágeis e tradicionais.");

                Curso trilhaMaster = new Curso();
                trilhaMaster.setTitulo("Trilha Master");
                trilhaMaster.setDescricao("Uma jornada completa para se tornar um especialista em dados.");

                cursoRepository.saveAll(java.util.List.of(powerBi, excelPro, gestaoProjetos, trilhaMaster));
                System.out.println("Cursos padrão criados com sucesso!");
            }

            // --- CRIAÇÃO E MATRÍCULA DO USUÁRIO PADRÃO ---
            // Só cria se o usuário 'aluno' não existir
            if (usuarioRepository.findByUsername("aluno").isEmpty()) {
                // Busca os cursos que acabamos de criar
                Curso cursoPowerBi = cursoRepository.findByTitulo("Power BI").orElse(null);
                Curso cursoExcel = cursoRepository.findByTitulo("Excel Pro").orElse(null);

                Usuario aluno = new Usuario();
                aluno.setUsername("aluno");
                aluno.setPassword(passwordEncoder.encode("senha123"));

                // Matricula o aluno nos cursos
                if (cursoPowerBi != null)
                    aluno.getCursos().add(cursoPowerBi);
                if (cursoExcel != null)
                    aluno.getCursos().add(cursoExcel);

                usuarioRepository.save(aluno);
                System.out.println("Usuário 'aluno' criado e matriculado em 2 cursos.");
            }
        };
    }
}