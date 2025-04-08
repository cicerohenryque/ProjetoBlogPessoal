package com.blogpessoal.dto;

import java.time.LocalDateTime;

public class PostagemDTO {

    private Long id;
    private String titulo;
    private String texto;
    private LocalDateTime data;
    private String nomeUsuario;
    private String descricaoTema;
    private Long usuarioId;
    private Long temaId;

    public PostagemDTO() {
    }

    public PostagemDTO(Long id, String titulo, String texto, LocalDateTime data, String nomeUsuario, String descricaoTema, Long usuarioId, Long temaId) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.data = data;
        this.nomeUsuario = nomeUsuario;
        this.descricaoTema = descricaoTema;
        this.usuarioId = usuarioId;
        this.temaId = temaId;
    }

    // getters e setters...

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getTexto() { return texto; }

    public void setTexto(String texto) { this.texto = texto; }

    public LocalDateTime getData() { return data; }

    public void setData(LocalDateTime data) { this.data = data; }

    public String getNomeUsuario() { return nomeUsuario; }

    public void setNomeUsuario(String nomeUsuario) { this.nomeUsuario = nomeUsuario; }

    public String getDescricaoTema() { return descricaoTema; }

    public void setDescricaoTema(String descricaoTema) { this.descricaoTema = descricaoTema; }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Long getTemaId() {
        return temaId;
    }

    public void setTemaId(Long temaId) {
        this.temaId = temaId;
    }
}
