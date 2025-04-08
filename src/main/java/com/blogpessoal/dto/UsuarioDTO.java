package com.blogpessoal.dto;

import com.blogpessoal.model.TipoUsuario;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String usuario;
    private String foto;
    private TipoUsuario tipo;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nome, String usuario, String foto, TipoUsuario tipo) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.foto = foto;
        this.tipo = tipo;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getFoto() { return foto; }

    public void setFoto(String foto) { this.foto = foto; }

    public TipoUsuario getTipo() { return tipo; }

    public void setTipo(TipoUsuario tipo) { this.tipo = tipo; }
}
