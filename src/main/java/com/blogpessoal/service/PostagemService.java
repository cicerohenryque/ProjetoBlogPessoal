package com.blogpessoal.service;

import com.blogpessoal.dto.PostagemDTO;
import com.blogpessoal.model.Postagem;
import com.blogpessoal.model.Tema;
import com.blogpessoal.model.Usuario;
import com.blogpessoal.repository.PostagemRepository;
import com.blogpessoal.repository.TemaRepository;
import com.blogpessoal.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostagemService {

    @Autowired
    public PostagemRepository postagemRepository;

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    public TemaRepository temaRepository;

    public List<PostagemDTO> listarTodasPostagens() {
        return postagemRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<PostagemDTO> buscarPostagemPorId(Long id) {
        return postagemRepository.findById(id)
                .map(this::toDTO);
    }

    public List<PostagemDTO> buscarPostagensPorTitulo(String titulo) {
        return postagemRepository.findAllByTituloContainingIgnoreCase(titulo)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<PostagemDTO> buscarPostagensPorUsuario(Long usuarioId) {
        return postagemRepository.findAllByUsuario_Id(usuarioId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public List<PostagemDTO> buscarPostagensPorTema(Long temaId) {
        return postagemRepository.findAllByTema_Id(temaId)
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<PostagemDTO> criarPostagem(PostagemDTO postagemDTO) {
        Postagem postagem = toEntity(postagemDTO);

        Optional<Usuario> usuario = usuarioRepository.findById(postagem.getUsuario().getId());
        Optional<Tema> tema = temaRepository.findById(postagem.getTema().getId());

        if (usuario.isPresent() && tema.isPresent()) {
            postagem.setUsuario(usuario.get());
            postagem.setTema(tema.get());

            Postagem salva = postagemRepository.save(postagem);
            return Optional.of(toDTO(salva));
        }

        return Optional.empty();
    }

    public Optional<PostagemDTO> atualizarPostagem(PostagemDTO postagemDTO) {
        if (postagemRepository.existsById(postagemDTO.getId())) {
            return criarPostagem(postagemDTO); // reutiliza a lógica de validação
        }
        return Optional.empty();
    }

    public void deletarPostagem(Long id) {
        postagemRepository.deleteById(id);
    }

    public PostagemDTO toDTO(Postagem postagem) {
        PostagemDTO dto = new PostagemDTO();
        dto.setId(postagem.getId());
        dto.setTitulo(postagem.getTitulo());
        dto.setTexto(postagem.getTexto());
        dto.setData(postagem.getData());
        dto.setNomeUsuario(postagem.getUsuario().getNome());
        dto.setDescricaoTema(postagem.getTema().getDescricao());
        dto.setUsuarioId(postagem.getUsuario().getId());
        dto.setTemaId(postagem.getTema().getId());
        return dto;
    }

    public Postagem toEntity(PostagemDTO dto) {
        Postagem postagem = new Postagem();
        postagem.setId(dto.getId());
        postagem.setTitulo(dto.getTitulo());
        postagem.setTexto(dto.getTexto());
        postagem.setData(dto.getData());

        Usuario usuario = new Usuario();
        usuario.setId(dto.getUsuarioId());
        postagem.setUsuario(usuario);

        Tema tema = new Tema();
        tema.setId(dto.getTemaId());
        postagem.setTema(tema);

        return postagem;
    }
}
