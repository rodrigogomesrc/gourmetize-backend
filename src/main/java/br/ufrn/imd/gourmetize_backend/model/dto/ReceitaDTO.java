package br.ufrn.imd.gourmetize_backend.model.dto;

import br.ufrn.imd.gourmetize_backend.model.Usuario;

public class ReceitaDTO {
    private Long id;
    private String titulo;
    private String descricao;
    private String ingredientes;
    private String preparo;
    private Usuario usuario;
    private Double mediaAvaliacao;

    public ReceitaDTO(Long id, String titulo, String descricao, String ingredientes, String preparo, Usuario usuario, Double mediaAvaliacao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.ingredientes = ingredientes;
        this.preparo = preparo;
        this.usuario = usuario;
        this.mediaAvaliacao = mediaAvaliacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Double getMediaAvaliacao() {
        return mediaAvaliacao;
    }

    public void setMediaAvaliacao(Double mediaAvaliacao) {
        this.mediaAvaliacao = mediaAvaliacao;
    }
}
