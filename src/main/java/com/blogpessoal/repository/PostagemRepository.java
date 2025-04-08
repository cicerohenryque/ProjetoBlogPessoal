package com.blogpessoal.repository;

import com.blogpessoal.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long> {

    List<Postagem> findAllByTituloContainingIgnoreCase(String titulo);

    List<Postagem> findAllByUsuario_Id(Long usuarioId);

    List<Postagem> findAllByTema_Id(Long temaId);
}
