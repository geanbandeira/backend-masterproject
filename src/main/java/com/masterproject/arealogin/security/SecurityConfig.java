package com.masterproject.arealogin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    // INJETADO: O handler que criamos para o sucesso do login com Google
    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler; 

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(authorize -> authorize
                // Permite a "sondagem" (preflight) do navegador
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                
                // Libera as rotas de autenticação manual E as de OAuth2 do Google
                .requestMatchers("/api/auth/**", "/oauth2/**").permitAll()
                
                // Protege as rotas de admin
                .requestMatchers("/api/admin/**").hasRole("ADMIN")
                
                // Qualquer outra requisição precisa de autenticação
                .anyRequest().authenticated()
            )
            // SEÇÃO ADICIONADA: Ativa e configura o login com Google
            .oauth2Login(oauth2 -> {
                oauth2.successHandler(oAuth2LoginSuccessHandler);
            })
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}