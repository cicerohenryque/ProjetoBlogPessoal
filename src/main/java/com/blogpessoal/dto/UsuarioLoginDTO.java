package com.blogpessoal.dto;

public class UsuarioLoginDTO {

    private Long id;
    private String nome;
    private String usuario;
    private String senha;
    private String token;
    private String foto;

    public UsuarioLoginDTO() {
    }

    public UsuarioLoginDTO(Long id, String nome, String usuario, String senha, String token, String foto) {
        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
        this.senha = senha;
        this.token = token;
        this.foto = foto;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getUsuario() { return usuario; }

    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getSenha() { return senha; }

    public void setSenha(String senha) { this.senha = senha; }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public String getFoto() { return foto; }

    public void setFoto(String foto) { this.foto = foto; }
}
