package com.blogpessoal.security;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.blogpessoal.model.Usuario;

public class DetalhesUsuarioImpl implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String usuario;
    private String senha;

    public DetalhesUsuarioImpl(Usuario usuario) {
        this.usuario = usuario.getUsuario();
        this.senha = usuario.getSenha();
    }

    public DetalhesUsuarioImpl() {}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // Sem perfis de acesso por enquanto
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
