package com.melateacafe.backend.controller;

import com.melateacafe.backend.dto.request.mesa.CreateMesaRequestDTO;
import com.melateacafe.backend.dto.request.mesa.UpdateMesaRequestDTO;
import com.melateacafe.backend.dto.response.mesa.MesaResponseDTO;
import com.melateacafe.backend.service.MesaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/mesa")
public class MesaController {
    private final Logger logger = Logger.getLogger(MesaController.class.getName());

    @Autowired
    private MesaService mesaService;

    @GetMapping
    public ResponseEntity<List<MesaResponseDTO>> getAll() {
        logger.info("Obteniendo todas las mesas");
        return ResponseEntity.ok(mesaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MesaResponseDTO> getById(@PathVariable Integer id) {
        logger.info("Obteniendo mesa con id: " + id);
        return ResponseEntity.ok(mesaService.findById(id));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<MesaResponseDTO>> getByEstado(@PathVariable Boolean estado) {
        logger.info("Obteniendo mesas con estado: " + estado);
        return ResponseEntity.ok(mesaService.findByEstado(estado));
    }

    @GetMapping("/estado-mesa/{idEstadoMesa}")
    public ResponseEntity<List<MesaResponseDTO>> getByEstadoMesa(@PathVariable Integer idEstadoMesa) {
        logger.info("Obteniendo mesas con estado de mesa: " + idEstadoMesa);
        return ResponseEntity.ok(mesaService.findByEstadoMesa(idEstadoMesa));
    }

    @GetMapping("/capacidad/{capacidad}")
    public ResponseEntity<List<MesaResponseDTO>> getByCapacidad(@PathVariable Integer capacidad) {
        logger.info("Obteniendo mesas con capacidad: " + capacidad);
        return ResponseEntity.ok(mesaService.findByCapacidad(capacidad));
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<MesaResponseDTO>> getDisponibles() {
        logger.info("Obteniendo mesas disponibles");
        return ResponseEntity.ok(mesaService.findDisponibles());
    }

    @PostMapping
    public ResponseEntity<MesaResponseDTO> create(@Valid @RequestBody CreateMesaRequestDTO request) {
        logger.info("Creando nueva mesa");
        return new ResponseEntity<>(mesaService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MesaResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateMesaRequestDTO request) {
        logger.info("Actualizando mesa con id: " + id);
        return ResponseEntity.ok(mesaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        logger.info("Eliminando mesa con id: " + id);
        mesaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}