    package com.blogpessoal.repository;

    import com.blogpessoal.model.Tema;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.stereotype.Repository;

    import java.util.List;

    @Repository
    public interface TemaRepository extends JpaRepository<Tema, Long> {

        List<Tema> findAllByDescricaoContainingIgnoreCase(String descricao);
    }
