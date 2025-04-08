package com.blogpessoal.controller;

import com.blogpessoal.dto.UsuarioDTO;
import com.blogpessoal.dto.UsuarioLoginDTO;
import com.blogpessoal.model.Usuario;
import com.blogpessoal.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Usuários", description = "Endpoints para gerenciamento de usuários")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Operation(summary = "Cadastrar um novo usuário", description = "Cria um novo usuário no sistema")
    @PostMapping("/cadastrar")
    public ResponseEntity<UsuarioDTO> cadastrar(@RequestBody Usuario usuario) {
        return usuarioService.cadastrarUsuario(usuario)
                .map(resp -> ResponseEntity.status(HttpStatus.CREATED).body(resp))
                .orElse(ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Autenticar usuário", description = "Realiza o login do usuário e retorna o token JWT")
    @PostMapping("/logar")
    public ResponseEntity<UsuarioLoginDTO> logar(@RequestBody UsuarioLoginDTO loginDTO) {
        return usuarioService.autenticarUsuario(Optional.of(loginDTO))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @Operation(summary = "Listar todos os usuários", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(@PathVariable Long id) {
        return usuarioService.buscarUsuarioPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/atualizar")
    public ResponseEntity<UsuarioDTO> atualizar(@RequestBody Usuario usuario) {
        return usuarioService.atualizarUsuario(usuario)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        usuarioService.deletarUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
