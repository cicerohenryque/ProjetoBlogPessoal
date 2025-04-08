package com.blogpessoal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TemaDTO {

    private Long id;

    @NotBlank(message = "O atributo 'descrição' é obrigatório!")
    @Size(min = 3, max = 100, message = "A descrição deve ter entre 3 e 100 caracteres.")
    private String descricao;

    public TemaDTO() {
    }

    public TemaDTO(Long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
