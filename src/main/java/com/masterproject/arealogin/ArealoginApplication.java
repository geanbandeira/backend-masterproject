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
        AulaRepository aulaRepository,
        PasswordEncoder passwordEncoder) {
    return args -> {

        // --- 1. CRIAÇÃO DOS CURSOS ---
        if (cursoRepository.count() == 0) {
            System.out.println("Criando cursos padrão...");
            Curso pbi = new Curso();
            pbi.setTitulo("Power BI");
            pbi.setDescricao("Domine a ferramenta de Business Intelligence mais poderosa do mercado.");

            Curso excel = new Curso();
            excel.setTitulo("Excel Pro");
            excel.setDescricao("Do básico ao avançado, torne-se um mestre em planilhas.");

            Curso gestao = new Curso();
            gestao.setTitulo("Gestão de Projetos Híbrida");
            gestao.setDescricao("Aprenda a gerenciar projetos com metodologias ágeis e tradicionais.");

            Curso trilha = new Curso();
            trilha.setTitulo("Trilha Master");
            trilha.setDescricao("Uma jornada completa para se tornar um especialista em dados.");

            cursoRepository.saveAll(List.of(pbi, excel, gestao, trilha));
        }

        // --- 2. CRIAÇÃO DAS AULAS PARA O CURSO DE GESTÃO DE PROJETOS ---
        if (aulaRepository.count() == 0) {
            System.out.println("Criando aulas de exemplo...");
            Curso gestao = cursoRepository.findByTitulo("Gestão de Projetos Híbrida").get();

            Aula aula1 = new Aula();
            aula1.setTitulo("Aula 1: O que é Gestão Híbrida?");
            aula1.setConteudo("Nesta aula introdutória, exploramos os conceitos fundamentais...");
            aula1.setVideoUrl("https://linkdovideodasuaprimeiraula.com");
            aula1.setGratuita(true); // Marcando a primeira aula como GRÁTIS
            aula1.setCurso(gestao);

            Aula aula2 = new Aula();
            aula2.setTitulo("Aula 2: Waterfall vs. Agile");
            aula2.setConteudo("Analisando as diferenças e quando usar cada metodologia...");
            aula2.setVideoUrl("https://linkdovideodasuasegundaaula.com");
            aula2.setCurso(gestao);
            
            Aula aula3 = new Aula();
            aula3.setTitulo("Aula 3: Ferramentas para o Gerente Híbrido");
            aula3.setConteudo("Conheça as principais ferramentas do mercado...");
            aula3.setVideoUrl("https://linkdovideodasuaterceiraaula.com");
            aula3.setCurso(gestao);

            aulaRepository.saveAll(List.of(aula1, aula2, aula3));
        }

        // --- 3. CRIAÇÃO DOS USUÁRIOS PADRÃO ---
        if (usuarioRepository.findByUsername("aluno").isEmpty()) {
            Usuario aluno = new Usuario();
            aluno.setUsername("aluno");
            aluno.setPassword(passwordEncoder.encode("senha123"));
            aluno.setRole("ROLE_USER");
            usuarioRepository.save(aluno);
            System.out.println("Usuário 'aluno' criado.");
        }
        
        if (usuarioRepository.findByUsername("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123"));
            admin.setRole("ROLE_ADMIN");
            usuarioRepository.save(admin);
            System.out.println("Usuário 'admin' criado.");
        }
    };
}
    
}