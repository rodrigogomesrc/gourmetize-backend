package br.ufrn.imd.gourmetize_backend.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Novo ID próprio para Carrinho

    @OneToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id", unique = true)
    private Usuario usuario;
    private List<String> ingredientes;

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<String> getIngredientes() {
        return this.ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

}
