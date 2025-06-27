package com.masterproject.arealogin.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {

    @GetMapping("/login")
    public String login() {
        // Retorna o nome do arquivo HTML (sem a extensão .html)
        return "login"; 
    }

    @GetMapping("/aulas")
    public String aulas() {
        // Esta é a página protegida
        return "aulas";
    }

    @GetMapping("/")
    public String home() {
        // Redireciona a raiz do site para a página de aulas (que exigirá login)
        return "redirect:/aulas";
    }
}