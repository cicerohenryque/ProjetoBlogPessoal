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

    public TemaService(TemaRepository temaRepository, ModelMapper modelMapper) {
    }

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

    public Optional<TemaDTO> criarTema(TemaDTO temaDTO) {
        Tema tema = modelMapper.map(temaDTO, Tema.class);
        Tema salvo = temaRepository.save(tema);
        return Optional.of(modelMapper.map(salvo, TemaDTO.class));
    }

    public Optional<TemaDTO> atualizarTema(TemaDTO temaDTO) {
        if (!temaRepository.existsById(temaDTO.getId()))
            return Optional.empty();

        Tema tema = modelMapper.map(temaDTO, Tema.class);
        Tema atualizado = temaRepository.save(tema);
        return Optional.of(modelMapper.map(atualizado, TemaDTO.class));
    }

    public void deletarTema(Long id) {
        temaRepository.deleteById(id);
    }

    public List<TemaDTO> buscarTodos() {
        return temaRepository.findAll()
                .stream()
                .map(tema -> modelMapper.map(tema, TemaDTO.class))
                .collect(Collectors.toList());
    }
}
