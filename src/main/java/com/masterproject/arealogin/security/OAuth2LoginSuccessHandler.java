package com.masterproject.arealogin.security;

import com.masterproject.arealogin.model.Usuario;
import com.masterproject.arealogin.repository.UsuarioRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.masterproject.arealogin.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        OAuth2User oauth2User = (OAuth2User) authentication.getPrincipal();
        String email = oauth2User.getAttribute("email");
        String nome = oauth2User.getAttribute("name");

        // Lógica "Encontre ou Crie"
        Usuario usuario = usuarioRepository.findByUsername(email)
            .orElseGet(() -> {
                Usuario newUser = new Usuario();
                newUser.setUsername(email);
                newUser.setPassword(passwordEncoder.encode("provided_by_google")); // Senha não usada
                newUser.setRole("ROLE_USER");
                return usuarioRepository.save(newUser);
            });

        // Gera um token JWT para o usuário
        String token = jwtUtil.generateToken(new CustomUserDetails(usuario));

        // Redireciona para o frontend com o token
        String redirectUrl = "https://masterproject.com.br/login-success.html?token=" + token;
        getRedirectStrategy().sendRedirect(request, response, redirectUrl);
    }
}