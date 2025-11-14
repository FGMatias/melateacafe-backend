package com.melateacafe.backend.controller;

import com.melateacafe.backend.dto.ProductoDTO;
import com.melateacafe.backend.entity.Producto;
import com.melateacafe.backend.service.ProductoService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/producto")
public class ProductoController {
    private final Logger logger = Logger.getLogger(ProductoController.class.getName());

    @Autowired
    private ProductoService productoService;

    @GetMapping()
    public ResponseEntity<List<Producto>> getAll() {
        logger.info("Obteniendo todos los productos");
        List<Producto> productos = productoService.findAll();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<Producto>> getActivos() {
        logger.info("Obteniendo todos los productos activos");
        List<Producto> productos = productoService.findActivos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Integer id) {
        try {
            logger.info("Obteniendo producto con id: " + id);
            Producto producto = productoService.findById(id);
            return ResponseEntity.ok(producto);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<Producto>> getByCategoria(@PathVariable Integer idCategoria) {
        logger.info("Obteniendo productos con categoria: " + idCategoria);
        List<Producto> productos = productoService.findByCategoria(idCategoria);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> findProductos(@RequestParam(name = "q") String query) {
        logger.info("Obteniendo productos con query: " + query);
        List<Producto> productos = productoService.findByNombre(query);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<Producto>> filterProductos(
            @RequestParam(required = false) Integer categoria,
            @RequestParam(required = false) BigDecimal precioMin,
            @RequestParam(required = false) BigDecimal precioMax,
            @RequestParam(required = false) String nombre
    ) {
        logger.info("Filtrando productos");
        List<Producto> productos = productoService.buscarProductos(categoria, precioMin, precioMax, nombre);
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/destacados")
    public ResponseEntity<List<Producto>> getDestacados() {
        logger.info("Obteniendo productos destacados");
        List<Producto> productos = productoService.findDestacados();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/stock-bajo")
    public ResponseEntity<List<Producto>> getWithStockBajo() {
        logger.info("Obteniendo productos con stock bajo");
        List<Producto> productos = productoService.findConStockBajo();
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody ProductoDTO productoDTO) {
        try {
            logger.info("Creando nuevo producto: " + productoDTO.getNombre());
            Producto producto = productoService.save(productoDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(producto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @Valid @RequestBody ProductoDTO productoDTO
    ) {
        try {
            logger.info("Actualizando producto con id: " + id);
            Producto producto = productoService.update(id, productoDTO);
            return ResponseEntity.ok(producto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            logger.info("Eliminando producto con id: " + id);
            productoService.delete(id);
            return ResponseEntity.ok(Map.of("message", "Producto desactivado correctamente"));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<?> actualizarStock(
            @PathVariable Integer id,
            @RequestBody Map<String, Integer> body
    ) {
        try {
            Integer cantidad = body.get("cantidad");
            if (cantidad == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Se requiere el campo 'cantidad'"));
            }

            logger.info("Actualizando stock del producto con id: " + id);
            productoService.actualizarStock(id, cantidad);
            return ResponseEntity.ok(Map.of("message", "Stock actualizado correctamente"));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
