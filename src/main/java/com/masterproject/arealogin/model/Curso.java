package com.masterproject.arealogin.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;

    // Relação: Um Curso tem muitas Aulas
    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL)
    private List<Aula> aulas;

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public List<Aula> getAulas() { return aulas; }
    public void setAulas(List<Aula> aulas) { this.aulas = aulas; }
}