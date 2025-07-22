package com.masterproject.arealogin.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @Column(columnDefinition = "TEXT") // Para conteúdos mais longos
    private String conteudo;
    private String videoUrl;

    // Relação: Muitas Aulas pertencem a um Curso
    @ManyToOne
    @JoinColumn(name = "curso_id")
    @JsonIgnore // Evita um loop infinito ao retornar os dados
    private Curso curso;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getConteudo() { return conteudo; }
    public void setConteudo(String conteudo) { this.conteudo = conteudo; }
    public String getVideoUrl() { return videoUrl; }
    public void setVideoUrl(String videoUrl) { this.videoUrl = videoUrl; }
    public Curso getCurso() { return curso; }
    public void setCurso(Curso curso) { this.curso = curso; }

private boolean gratuita = false; // Por padrão, uma aula não é grátis

// Adicione os Getters e Setters para o novo campo
public boolean isGratuita() {
    return gratuita;
}
public void setGratuita(boolean gratuita) {
    this.gratuita = gratuita;
}
}