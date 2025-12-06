package com.melateacafe.backend.controller;

import com.melateacafe.backend.dto.request.usuario.CreateUsuarioRequestDTO;
import com.melateacafe.backend.dto.request.usuario.UpdateUsuarioRequestDTO;
import com.melateacafe.backend.dto.response.usuario.UsuarioResponseDTO;
import com.melateacafe.backend.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/usuario")
public class UsuarioController {
    private final Logger logger = Logger.getLogger(UsuarioController.class.getName());

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> getAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.findById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UsuarioResponseDTO> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(usuarioService.findByUsername(username));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> create(@Valid @RequestBody CreateUsuarioRequestDTO request) {
        return new ResponseEntity<>(usuarioService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateUsuarioRequestDTO request) {
        return ResponseEntity.ok(usuarioService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        usuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
