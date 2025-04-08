package com.blogpessoal.controller;

import com.blogpessoal.dto.PostagemDTO;
import com.blogpessoal.model.Postagem;
import com.blogpessoal.service.PostagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Postagens", description = "Endpoints para gerenciamento de postagens")
public class PostagemController {

    @Autowired
    private PostagemService postagemService;

    @Operation(summary = "Listar todas as postagens", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public ResponseEntity<List<PostagemDTO>> listarTodas() {
        return ResponseEntity.ok(postagemService.listarTodasPostagens());
    }

    @Operation(summary = "Buscar postagem por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{id}")
    public ResponseEntity<PostagemDTO> buscarPorId(@PathVariable Long id) {
        return postagemService.buscarPostagemPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/titulo/{titulo}")
    public ResponseEntity<List<PostagemDTO>> buscarPorTitulo(@PathVariable String titulo) {
        return ResponseEntity.ok(postagemService.buscarPostagensPorTitulo(titulo));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PostagemDTO>> buscarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(postagemService.buscarPostagensPorUsuario(usuarioId));
    }

    @GetMapping("/tema/{temaId}")
    public ResponseEntity<List<PostagemDTO>> buscarPorTema(@PathVariable Long temaId) {
        return ResponseEntity.ok(postagemService.buscarPostagensPorTema(temaId));
    }

    @PostMapping
    public ResponseEntity<PostagemDTO> criar(@RequestBody PostagemDTO dto) {
        return postagemService.criarPostagem(dto)
                .map(p -> ResponseEntity.status(HttpStatus.CREATED).body(p))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping
    public ResponseEntity<PostagemDTO> atualizar(@RequestBody PostagemDTO dto) {
        return postagemService.atualizarPostagem(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        postagemService.deletarPostagem(id);
        return ResponseEntity.noContent().build();
    }
}