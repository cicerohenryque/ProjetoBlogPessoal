package com.blogpessoal.dto;

import java.time.LocalDateTime;

public class PostagemDTO {

    private Long id;
    private String titulo;
    private String texto;
    private LocalDateTime data;
    private String nomeUsuario;
    private String descricaoTema;

    public PostagemDTO() {
    }

    public PostagemDTO(Long id, String titulo, String texto, LocalDateTime data, String nomeUsuario, String descricaoTema) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.data = data;
        this.nomeUsuario = nomeUsuario;
        this.descricaoTema = descricaoTema;
    }

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
}
