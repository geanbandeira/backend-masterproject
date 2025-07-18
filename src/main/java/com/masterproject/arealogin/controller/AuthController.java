package com.masterproject.arealogin.controller;

import com.masterproject.arealogin.dto.LoginRequest;
import com.masterproject.arealogin.dto.LoginResponse;
import com.masterproject.arealogin.model.Usuario; // IMPORT ADICIONADO
import com.masterproject.arealogin.repository.UsuarioRepository; // IMPORT ADICIONADO
import com.masterproject.arealogin.security.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder; // IMPORT ADICIONADO
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    // LINHAS ADICIONADAS PARA CORRIGIR O ERRO
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    // FIM DAS LINHAS ADICIONADAS

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@Valid @RequestBody LoginRequest loginRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.username(), loginRequest.password())
            );
        } catch (Exception e) {
            throw new Exception("Usuário ou senha inválidos", e);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.username());
        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new LoginResponse(jwt));
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody LoginRequest registerRequest) {
        // 1. Verifica se o nome de usuário já existe
        if (usuarioRepository.findByUsername(registerRequest.username()).isPresent()) {
            return ResponseEntity.badRequest().body("Erro: Nome de usuário já existe!");
        }

        // 2. Cria um novo usuário
        Usuario newUser = new Usuario();
        newUser.setUsername(registerRequest.username());
        // 3. CRIPTOGRAFA a senha antes de salvar (MUITO IMPORTANTE)
        newUser.setPassword(passwordEncoder.encode(registerRequest.password()));

        // 4. Salva o novo usuário no banco de dados
        usuarioRepository.save(newUser);

        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }
}