package br.ufrn.imd.gourmetize_backend.model;

import jakarta.persistence.*;

@Entity
public class AnotacaoReceita {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String anotacao;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "receita_id")
    private Receita receita;

    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    public String getAnotacao() {
      return anotacao;
    }

    public void setAnotacao(String anotacao) {
      this.anotacao = anotacao;
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
}
