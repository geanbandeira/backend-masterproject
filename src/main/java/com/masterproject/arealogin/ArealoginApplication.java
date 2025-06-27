package com.masterproject.arealogin;

import com.masterproject.arealogin.model.Usuario;
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

    // Este Bean será executado na inicialização da aplicação
    @Bean
    public CommandLineRunner initData(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            // Cria um usuário padrão para testes
            Usuario user = new Usuario();
            user.setUsername("aluno");
            // IMPORTANTE: Sempre salve a senha codificada!
            user.setPassword(passwordEncoder.encode("senha123")); 
            
            // Salva o usuário no banco de dados se ele ainda não existir
            if (usuarioRepository.findByUsername(user.getUsername()).isEmpty()) {
                usuarioRepository.save(user);
                System.out.println("Usuário 'aluno' criado com senha 'senha123'");
            }
        };
    }
}