package com.melateacafe.backend.controller;

import com.melateacafe.backend.dto.TrabajadorDTO;
import com.melateacafe.backend.entity.Trabajador;
import com.melateacafe.backend.service.TrabajadorService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/trabajador")
public class TrabajadorController {
    private final Logger logger = Logger.getLogger(TrabajadorController.class.getName());

    @Autowired
    private TrabajadorService trabajadorService;

    @GetMapping
    public ResponseEntity<List<Trabajador>> getAll() {
        logger.info("Obteniendo todos los trabajadores");
        List<Trabajador> trabajadores = trabajadorService.findAll();
        return ResponseEntity.ok(trabajadores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            logger.info("Obteniendo trabajador con id: " + id);
            Trabajador trabajador = trabajadorService.findById(id);
            return ResponseEntity.ok(trabajador);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/cargo/{idCargo}")
    public ResponseEntity<List<Trabajador>> getByCargo(@PathVariable Integer idCargo) {
        logger.info("Obteniendo trabajadores con cargo: " + idCargo);
        List<Trabajador> trabajadores = trabajadorService.findByCargo(idCargo);
        return ResponseEntity.ok(trabajadores);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Trabajador>> getByEstado(@PathVariable boolean estado) {
        logger.info("Obteniendo trabajadores con estado: " + estado);
        List<Trabajador> trabajadores = trabajadorService.findByEstado(estado);
        return ResponseEntity.ok(trabajadores);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody TrabajadorDTO trabajadorDTO) {
        try {
            logger.info("Creando trabajador: " + trabajadorDTO.getNombres());
            Trabajador trabajador = trabajadorService.save(trabajadorDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(trabajador);
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
            @Valid @RequestBody TrabajadorDTO trabajadorDTO
    ) {
        try {
            logger.info("Actualizando trabajador con id: " + id);
            Trabajador trabajador = trabajadorService.update(id, trabajadorDTO);
            return ResponseEntity.ok(trabajador);
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
            logger.info("Eliminando trabajador con id: " + id);
            trabajadorService.delete(id);
            return ResponseEntity.ok(Map.of("message", "Trabajador desactivado exitosamente"));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
