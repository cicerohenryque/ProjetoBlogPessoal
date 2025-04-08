package com.blogpessoal.service;

import com.blogpessoal.dto.UsuarioDTO;
import com.blogpessoal.dto.UsuarioLoginDTO;
import com.blogpessoal.model.Usuario;
import com.blogpessoal.repository.UsuarioRepository;
import com.blogpessoal.security.JwtService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    public UsuarioRepository usuarioRepository;

    @Autowired
    public JwtService jwtService;

    @Autowired
    public ModelMapper modelMapper;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Optional<UsuarioDTO> cadastrarUsuario(Usuario usuario) {
        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
            return Optional.empty();

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        Usuario usuarioSalvo = usuarioRepository.save(usuario);
        return Optional.of(modelMapper.map(usuarioSalvo, UsuarioDTO.class));
    }

    public Optional<UsuarioDTO> atualizarUsuario(Usuario usuario) {
        if (!usuarioRepository.existsById(usuario.getId()))
            return Optional.empty();

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        Usuario atualizado = usuarioRepository.save(usuario);
        return Optional.of(modelMapper.map(atualizado, UsuarioDTO.class));
    }

    public Optional<UsuarioLoginDTO> autenticarUsuario(Optional<UsuarioLoginDTO> usuarioLoginDTO) {
        if (usuarioLoginDTO.isEmpty()) return Optional.empty();

        Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLoginDTO.get().getUsuario());

        if (usuario.isPresent() && passwordEncoder.matches(usuarioLoginDTO.get().getSenha(), usuario.get().getSenha())) {
            Usuario usuarioEncontrado = usuario.get();
            String token = jwtService.gerarToken(usuarioEncontrado.getUsuario());

            UsuarioLoginDTO loginDTO = modelMapper.map(usuarioEncontrado, UsuarioLoginDTO.class);
            loginDTO.setToken(token);
            loginDTO.setSenha(""); // Não retornar a senha criptografada

            return Optional.of(loginDTO);
        }

        return Optional.empty();
    }

    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarUsuarioPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public void deletarUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
