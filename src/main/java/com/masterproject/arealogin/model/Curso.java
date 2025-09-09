package com.masterproject.arealogin.model;

import jakarta.persistence.*;
import java.util.List;
import jakarta.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;

    @Column(unique = true, nullable = false) // Garante que cada curso tenha um apelido único
    private String slug;

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
    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

@ManyToMany(mappedBy = "cursos")
@JsonIgnore // Importante para não criar um loop de dados
private java.util.Set<Usuario> usuariosMatriculados = new java.util.HashSet<>();

// Getter e Setter
public java.util.Set<Usuario> getUsuariosMatriculados() { return usuariosMatriculados; }
public void setUsuariosMatriculados(java.util.Set<Usuario> usuariosMatriculados) { this.usuariosMatriculados = usuariosMatriculados; }

}
