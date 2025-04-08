package com.blogpessoal.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "tb_postagens")
public class Postagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O título é obrigatório!")
    @Size(min = 2, max = 100, message = "O título deve conter no mínimo 2 e no máximo 100 caracteres")
    private String titulo;

    @NotBlank(message = "O texto é obrigatório!")
    @Size(min = 5, max = 1000, message = "O texto deve conter no mínimo 5 e no máximo 1000 caracteres")
    private String texto;

    @UpdateTimestamp
    private LocalDateTime data;

    @ManyToOne
    @JsonIgnoreProperties("postagens")
    private Tema tema;

    @ManyToOne
    @JsonIgnoreProperties("postagens")
    private Usuario usuario;

    public Postagem() {}

    public Postagem(Long id, String titulo, String texto, LocalDateTime data) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.data = data;
    }

    public Postagem(Object o, String titulo, String texto, LocalDate now, Usuario usuario, Tema tema) {
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

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public Tema getTema() {
        return tema;
    }

    public void setTema(Tema tema) {
        this.tema = tema;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}