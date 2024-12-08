package br.ufrn.imd.gourmetize_backend.model;


import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class Receita {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String titulo;
    private String descricao;
    private String ingredientes;
    private String preparo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @JsonIgnore
    @OneToMany(mappedBy = "receita", cascade = CascadeType.REMOVE)
    private Set<Avaliacao> avaliacoes;

    @ManyToMany
    @JoinTable(
        name = "receita_etiqueta",
        joinColumns = @JoinColumn(name = "receita_id"),
        inverseJoinColumns = @JoinColumn(name = "etiqueta_id")
    )
    private Set<Etiqueta> etiquetas;


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public String getDescricao() {
        return descricao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPreparo() {
        return preparo;
    }

    public void setPreparo(String preparo) {
        this.preparo = preparo;
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
