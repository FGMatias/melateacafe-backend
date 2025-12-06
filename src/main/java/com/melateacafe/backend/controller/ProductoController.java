package com.melateacafe.backend.controller;

import com.melateacafe.backend.dto.request.producto.CreateProductoRequestDTO;
import com.melateacafe.backend.dto.request.producto.UpdateProductoRequestDTO;
import com.melateacafe.backend.dto.response.producto.ProductoResponseDTO;
import com.melateacafe.backend.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/producto")
public class ProductoController {
    private final Logger logger = Logger.getLogger(ProductoController.class.getName());

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoResponseDTO>> getAll() {
        logger.info("Obteniendo todos los productos");
        return ResponseEntity.ok(productoService.findAll());
    }

    @GetMapping("/activos")
    public ResponseEntity<List<ProductoResponseDTO>> getActivos() {
        logger.info("Obteniendo todos los productos activos");
        return ResponseEntity.ok(productoService.findActivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> findById(@PathVariable Integer id) {
        logger.info("Obteniendo producto con id: " + id);
        return ResponseEntity.ok(productoService.findById(id));
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<ProductoResponseDTO>> getByCategoria(@PathVariable Integer idCategoria) {
        logger.info("Obteniendo productos con categoría: " + idCategoria);
        return ResponseEntity.ok(productoService.findByCategoria(idCategoria));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ProductoResponseDTO>> findProductos(@RequestParam(name = "q") String query) {
        logger.info("Obteniendo productos con query: " + query);
        return ResponseEntity.ok(productoService.findByNombre(query));
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<ProductoResponseDTO>> filterProductos(
            @RequestParam(required = false) Integer categoria,
            @RequestParam(required = false) BigDecimal precioMin,
            @RequestParam(required = false) BigDecimal precioMax,
            @RequestParam(required = false) String nombre
    ) {
        logger.info("Filtrando productos");
        return ResponseEntity.ok(productoService.buscarProductos(categoria, precioMin, precioMax, nombre));
    }

    @GetMapping("/destacados")
    public ResponseEntity<List<ProductoResponseDTO>> getDestacados() {
        logger.info("Obteniendo productos destacados");
        return ResponseEntity.ok(productoService.findDestacados());
    }

    @GetMapping("/stock-bajo")
    public ResponseEntity<List<ProductoResponseDTO>> getWithStockBajo() {
        logger.info("Obteniendo productos con stock bajo");
        return ResponseEntity.ok(productoService.findWithStockBajo());
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ProductoResponseDTO>> getByEstado(@PathVariable Boolean estado) {
        logger.info("Obteniendo productos con estado: " + estado);
        return ResponseEntity.ok(productoService.findByEstado(estado));
    }

    @PostMapping
    public ResponseEntity<ProductoResponseDTO> create(@Valid @RequestBody CreateProductoRequestDTO request) {
        logger.info("Creando nuevo producto");
        return new ResponseEntity<>(productoService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateProductoRequestDTO request) {
        logger.info("Actualizando producto con id: " + id);
        return ResponseEntity.ok(productoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        logger.info("Eliminando producto con id: " + id);
        productoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
