package com.blogpessoal.service;

import com.blogpessoal.dto.TemaDTO;
import com.blogpessoal.model.Tema;
import com.blogpessoal.repository.TemaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TemaService {

    @Autowired
    private TemaRepository temaRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TemaDTO> listarTemas() {
        return temaRepository.findAll()
                .stream()
                .map(t -> modelMapper.map(t, TemaDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<TemaDTO> buscarTemaPorId(Long id) {
        return temaRepository.findById(id)
                .map(t -> modelMapper.map(t, TemaDTO.class));
    }

    public List<TemaDTO> buscarTemasPorDescricao(String descricao) {
        return temaRepository.findAllByDescricaoContainingIgnoreCase(descricao)
                .stream()
                .map(t -> modelMapper.map(t, TemaDTO.class))
                .collect(Collectors.toList());
    }

    public Optional<TemaDTO> criarTema(TemaDTO tema) {
        Tema temas = new Tema();
        TemaDTO temaDTO = null;
        tema.setDescricao(temaDTO.getDescricao());
        temaRepository.save(temas);
        Object save = null;
        return Optional.of(modelMapper.map(save, TemaDTO.class));
    }

    public Optional<TemaDTO> atualizarTema(TemaDTO tema) {
        if (!temaRepository.existsById(tema.getId()))
            return Optional.empty();

        Tema temas = new Tema();
        TemaDTO temaDTO = null;
        tema.setDescricao(temaDTO.getDescricao());
        temaRepository.save(temas);
        Object save = null;
        Tema atualizado = temaRepository.save(temas);
        return Optional.of(modelMapper.map(atualizado, TemaDTO.class));
    }

    public void deletarTema(Long id) {
        temaRepository.deleteById(id);
    }
}
