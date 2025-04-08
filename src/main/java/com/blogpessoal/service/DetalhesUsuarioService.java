package com.blogpessoal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import com.blogpessoal.model.Usuario;
import com.blogpessoal.repository.UsuarioRepository;
import com.blogpessoal.security.DetalhesUsuarioImpl;

@Service
public class DetalhesUsuarioService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuario = usuarioRepository.findByUsuario(username);

        if (usuario.isEmpty()) {
            throw new UsernameNotFoundException("Usuário não encontrado");
        }

        return new DetalhesUsuarioImpl(usuario.get());
    }
}
