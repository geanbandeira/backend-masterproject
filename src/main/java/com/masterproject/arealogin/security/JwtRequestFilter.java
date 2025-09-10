package com.masterproject.arealogin.security;

import com.masterproject.arealogin.service.UserDetailsServiceImpl; // Verifique se este é o caminho correto para seu serviço
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String username = null;
        String jwt = null;

        // --- INÍCIO DOS LOGS DE DEPURACÃO ---
        System.out.println("\n--- [JwtRequestFilter] Processando requisição para: " + request.getRequestURI() + " ---");
        System.out.println("[JwtRequestFilter] Cabeçalho Authorization: " + authorizationHeader);

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwt = authorizationHeader.substring(7);
            try {
                username = jwtUtil.extractUsername(jwt);
                System.out.println("[JwtRequestFilter] Username extraído do token: '" + username + "'");
            } catch (ExpiredJwtException e) {
                System.out.println("[JwtRequestFilter] ERRO: O token JWT expirou. Mensagem: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("[JwtRequestFilter] ERRO ao extrair username do token: " + e.getMessage());
            }
        } else {
            System.out.println("[JwtRequestFilter] Token não encontrado ou sem prefixo 'Bearer'.");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            System.out.println("[JwtRequestFilter] Contexto de segurança está nulo. Tentando carregar usuário: '" + username + "'");

            try {
                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                System.out.println("[JwtRequestFilter] UserDetails carregado: '" + userDetails.getUsername() + "' com permissões: " + userDetails.getAuthorities());

                if (jwtUtil.validateToken(jwt, userDetails)) {
                    System.out.println("[JwtRequestFilter] Token é VÁLIDO.");

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    System.out.println("[JwtRequestFilter] Usuário AUTENTICADO e contexto de segurança populado com sucesso.");

                } else {
                     System.out.println("[JwtRequestFilter] Falha na validação do token.");
                }
            } catch (Exception e) {
                System.out.println("[JwtRequestFilter] ERRO ao carregar UserDetails ou validar token: " + e.getMessage());
            }
        } else if (username != null) {
            System.out.println("[JwtRequestFilter] Contexto de segurança NÃO ESTÁ nulo. Usuário já estava autenticado.");
        }
        
        System.out.println("--- [JwtRequestFilter] Finalizando filtro e passando para o próximo na cadeia ---");
        // --- FIM DOS LOGS DE DEPURACÃO ---

        chain.doFilter(request, response);
    }
}