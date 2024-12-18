package br.ufrn.imd.gourmetize_backend.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;

    @Column(unique = true)
    private String email;
    private String senha;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private Set<Receita> receitas;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private Set<Etiqueta> etiquetas;

    @OneToMany(mappedBy = "usuario")
    @JsonIgnore
    private Set<Avaliacao> avaliacoes;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Set<Receita> getReceitas() {
        return receitas;
    }

    public void setReceitas(Set<Receita> receitas) {
        this.receitas = receitas;
    }

    public Set<Etiqueta> getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(Set<Etiqueta> etiquetas) {
        this.etiquetas = etiquetas;
    }

    public Set<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(Set<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }
}
