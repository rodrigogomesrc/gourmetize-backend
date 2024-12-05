package br.ufrn.imd.gourmetize_backend.model;

import jakarta.persistence.*;

@Entity
public class Avaliacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int nota;
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Avaliacao(String comentario, int nota, Usuario user) {
        this.comentario = comentario;
        this.nota = nota;
        this.usuario = user;
    }

    public Avaliacao(Long id, String comentario, int nota, Usuario user) {
        this.id = id;
        this.comentario = comentario;
        this.nota = nota;
        this.usuario = user;

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

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
}
