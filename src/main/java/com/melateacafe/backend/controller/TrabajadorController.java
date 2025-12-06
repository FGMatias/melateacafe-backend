package com.melateacafe.backend.controller;

import com.melateacafe.backend.dto.request.trabajador.CreateTrabajadorRequestDTO;
import com.melateacafe.backend.dto.request.trabajador.UpdateTrabajadorRequestDTO;
import com.melateacafe.backend.dto.response.trabajador.TrabajadorResponseDTO;
import com.melateacafe.backend.service.TrabajadorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/trabajador")
public class TrabajadorController {
    private final Logger logger = Logger.getLogger(TrabajadorController.class.getName());

    @Autowired
    private TrabajadorService trabajadorService;

    @GetMapping
    public ResponseEntity<List<TrabajadorResponseDTO>> getAll() {
        return ResponseEntity.ok(trabajadorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrabajadorResponseDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok(trabajadorService.findById(id));
    }

    @GetMapping("/cargo/{idCargo}")
    public ResponseEntity<List<TrabajadorResponseDTO>> getByCargo(@PathVariable Integer idCargo) {
        return ResponseEntity.ok(trabajadorService.findByCargo(idCargo));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<TrabajadorResponseDTO>> getByEstado(@PathVariable Boolean estado) {
        return ResponseEntity.ok(trabajadorService.findByEstado(estado));
    }

    @PostMapping
    public ResponseEntity<TrabajadorResponseDTO> create(@Valid @RequestBody CreateTrabajadorRequestDTO request) {
        return new ResponseEntity<>(trabajadorService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TrabajadorResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateTrabajadorRequestDTO request) {
        return ResponseEntity.ok(trabajadorService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        trabajadorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
