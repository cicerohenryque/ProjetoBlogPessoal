package com.blogpessoal.testes.Integracao;

import com.blogpessoal.dto.TemaDTO;
import com.blogpessoal.model.Tema;
import com.blogpessoal.repository.TemaRepository;
import com.blogpessoal.service.TemaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Import({TemaService.class, ModelMapper.class})
@ActiveProfiles("test")
class TemaServiceIntegracao {

    @Autowired
    private TemaService temaService;

    @Autowired
    private TemaRepository temaRepository;

    @BeforeEach
    void setup() {
        temaRepository.deleteAll(); // limpa o banco H2 antes de cada teste
        temaRepository.save(new Tema(null, "Java"));
        temaRepository.save(new Tema(null, "Spring Boot"));
    }

    @Test
    void deveBuscarTodosOsTemas() {
        List<TemaDTO> temas = temaService.buscarTodos();
        assertEquals(2, temas.size());
    }

    @Test
    void deveBuscarTemaPorId() {
        Tema tema = temaRepository.save(new Tema(null, "Docker"));

        Optional<TemaDTO> resultado = temaService.buscarTemaPorId(tema.getId());

        assertTrue(resultado.isPresent());
        assertEquals("Docker", resultado.get().getDescricao());
    }

    @Test
    void deveBuscarPorDescricaoIgnorandoCase() {
        List<TemaDTO> resultado = temaService.buscarTemasPorDescricao("spring");
        assertEquals(1, resultado.size());
        assertEquals("Spring Boot", resultado.get(0).getDescricao());
    }

    @Test
    public void deveCadastrarTema() {
        // Arrange: cria um novo DTO com a descrição esperada
        TemaDTO novoTema = new TemaDTO(null, "Kubernetes");

        Optional<TemaDTO> resultado = temaService.criarTema(novoTema);

        assertNotNull(resultado, "O resultado não deveria ser nulo");
        assertNotNull(resultado.get().getId(), "O ID deveria ser preenchido após o salvamento");
        assertEquals("Kubernetes", resultado.get().getDescricao(), "A descrição deveria ser 'Kubernetes'");
    }


    @Test
    void deveAtualizarTema() {
        Tema tema = temaRepository.save(new Tema(null, "CI/CD"));

        TemaDTO dtoAtualizado = new TemaDTO(tema.getId(), "DevOps");
        Optional<TemaDTO> resultado = temaService.atualizarTema(dtoAtualizado);

        assertEquals("DevOps", resultado.get().getDescricao());
    }

    @Test
    void deveApagarTema() {
        Tema tema = temaRepository.save(new Tema(null, "Cloud"));

        temaService.deletarTema(tema.getId());

        assertFalse(temaRepository.findById(tema.getId()).isPresent());
    }
}
