package com.blogpessoal.controller;

import com.blogpessoal.dto.TemaDTO;
import com.blogpessoal.model.Tema;
import com.blogpessoal.service.TemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@Tag(name = "Temas", description = "Endpoints para gerenciamento de temas")
public class TemaController {

    @Autowired
    private TemaService temaService;

    @Operation(summary = "Listar todos os temas", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping
    public ResponseEntity<List<TemaDTO>> listarTemas() {
        return ResponseEntity.ok(temaService.listarTemas());
    }

    @Operation(summary = "Buscar tema por ID", security = @SecurityRequirement(name = "bearerAuth"))
    @GetMapping("/{id}")
    public ResponseEntity<TemaDTO> buscarPorId(@PathVariable Long id) {
        return temaService.buscarTemaPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<TemaDTO> criar(@RequestBody TemaDTO dto) {
        return temaService.criarTema(dto)
                .map(t -> ResponseEntity.status(HttpStatus.CREATED).body(t))
                .orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping
    public ResponseEntity<TemaDTO> atualizar(@RequestBody TemaDTO dto) {
        return temaService.atualizarTema(dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        temaService.deletarTema(id);
        return ResponseEntity.noContent().build();
    }
}