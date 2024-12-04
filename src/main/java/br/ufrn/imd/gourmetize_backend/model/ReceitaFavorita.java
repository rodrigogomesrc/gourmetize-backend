package br.ufrn.imd.gourmetize_backend.model;

import jakarta.persistence.*;

@Entity
public class ReceitaFavorita {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receita receita;

    public ReceitaFavorita(Usuario usuario, Receita receita) {
        this.usuario = usuario;
        this.receita = receita;
    }

    public ReceitaFavorita() {

    }

    public Receita getReceita() {
        return receita;
    }

    public void setReceita(Receita receita) {
        this.receita = receita;
    }

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

}
