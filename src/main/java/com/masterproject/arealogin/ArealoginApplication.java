package com.masterproject.arealogin;

import com.masterproject.arealogin.model.Aula;
import com.masterproject.arealogin.model.Curso;
import com.masterproject.arealogin.model.Usuario;
import com.masterproject.arealogin.repository.AulaRepository;
import com.masterproject.arealogin.repository.CursoRepository;
import com.masterproject.arealogin.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication
public class ArealoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArealoginApplication.class, args);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner initData(
            UsuarioRepository usuarioRepository,
            CursoRepository cursoRepository,
            AulaRepository aulaRepository, // Adicionado para criar aulas
            PasswordEncoder passwordEncoder) {
        return args -> {

            // --- CRIAÇÃO DOS CURSOS ---
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

                cursoRepository.saveAll(List.of(powerBi, excelPro, gestaoProjetos, trilhaMaster));
                System.out.println("Cursos padrão criados com sucesso!");
            }

            // --- CRIAÇÃO DAS AULAS DE TESTE ---
            if (aulaRepository.count() == 0 && cursoRepository.count() > 0) {
                Curso powerBi = cursoRepository.findByTitulo("Power BI").get();
                
                Aula aula1 = new Aula();
                aula1.setTitulo("Aula 1 - Conhecendo a Interface");
                aula1.setConteudo("Esta é a primeira aula do curso de Power BI. Ela é gratuita para demonstração.");
                aula1.setGratuita(true); // Marcando a primeira aula como GRÁTIS
                aula1.setCurso(powerBi);

                Aula aula2 = new Aula();
                aula2.setTitulo("Aula 2 - Importando e Tratando Dados");
                aula2.setConteudo("Conteúdo exclusivo para alunos matriculados.");
                aula2.setCurso(powerBi);
                
                aulaRepository.saveAll(List.of(aula1, aula2));
                System.out.println("Aulas de teste para Power BI criadas.");
            }

            // --- CRIAÇÃO DOS USUÁRIOS PADRÃO ---
            if (usuarioRepository.findByUsername("aluno").isEmpty()) {
                Curso cursoPowerBi = cursoRepository.findByTitulo("Power BI").get();
                Curso cursoExcel = cursoRepository.findByTitulo("Excel Pro").get();

                Usuario aluno = new Usuario();
                aluno.setUsername("aluno");
                aluno.setPassword(passwordEncoder.encode("senha123"));
                aluno.setRole("ROLE_USER"); // Define o papel de usuário comum

                // Matricula o aluno nos cursos
                aluno.getCursos().add(cursoPowerBi);
                aluno.getCursos().add(cursoExcel);

                usuarioRepository.save(aluno);
                System.out.println("Usuário 'aluno' criado e matriculado em 2 cursos.");
            }
            
            if (usuarioRepository.findByUsername("admin").isEmpty()) {
                Usuario admin = new Usuario();
                admin.setUsername("admin");
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole("ROLE_ADMIN"); // Define o papel de administrador
                usuarioRepository.save(admin);
                System.out.println("Usuário 'admin' criado.");
            }
        };
    }
}