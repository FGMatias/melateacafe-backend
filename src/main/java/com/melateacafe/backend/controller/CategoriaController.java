package com.melateacafe.backend.controller;

import com.melateacafe.backend.dto.request.categoria.CreateCategoriaRequestDTO;
import com.melateacafe.backend.dto.request.categoria.UpdateCategoriaRequestDTO;
import com.melateacafe.backend.dto.response.categoria.CategoriaResponseDTO;
import com.melateacafe.backend.entity.CategoriaProducto;
import com.melateacafe.backend.service.CategoriaProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/categoria")
public class CategoriaController {
    private final Logger logger = Logger.getLogger(CategoriaController.class.getName());

    @Autowired
    private CategoriaProductoService categoriaProductoService;

    @GetMapping
    public ResponseEntity<List<CategoriaResponseDTO>> getAll() {
        logger.info("Obteniendo todas las categorías");
        return ResponseEntity.ok(categoriaProductoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> getById(@PathVariable Integer id) {
        logger.info("Obteniendo categoría con id: " + id);
        return ResponseEntity.ok(categoriaProductoService.findById(id));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<CategoriaResponseDTO>> getByEstado(@PathVariable Boolean estado) {
        logger.info("Obteniendo categorías con estado: " + estado);
        return ResponseEntity.ok(categoriaProductoService.findByEstado(estado));
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> create(@Valid @RequestBody CreateCategoriaRequestDTO request) {
        logger.info("Creando nueva categoría");
        return new ResponseEntity<>(categoriaProductoService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateCategoriaRequestDTO request) {
        logger.info("Actualizando categoría con id: " + id);
        return ResponseEntity.ok(categoriaProductoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        logger.info("Eliminando categoría con id: " + id);
        categoriaProductoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
