package com.melateacafe.backend.controller;

import com.melateacafe.backend.dto.request.reserva.CreateReservaRequestDTO;
import com.melateacafe.backend.dto.request.reserva.UpdateReservaRequestDTO;
import com.melateacafe.backend.dto.response.reserva.ReservaResponseDTO;
import com.melateacafe.backend.service.ReservaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/reserva")
public class ReservaController {
    private final Logger logger = Logger.getLogger(ReservaController.class.getName());

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public ResponseEntity<List<ReservaResponseDTO>> getAll() {
        logger.info("Obteniendo todas las reservas");
        return ResponseEntity.ok(reservaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> getById(@PathVariable Integer id) {
        logger.info("Obteniendo reserva con id: " + id);
        return ResponseEntity.ok(reservaService.findById(id));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ReservaResponseDTO>> getByEstado(@PathVariable Boolean estado) {
        logger.info("Obteniendo reservas con estado: " + estado);
        return ResponseEntity.ok(reservaService.findByEstado(estado));
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<ReservaResponseDTO>> getByFecha(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        logger.info("Obteniendo reservas para la fecha: " + fecha);
        return ResponseEntity.ok(reservaService.findByFecha(fecha));
    }

    @GetMapping("/mesa/{idMesa}")
    public ResponseEntity<List<ReservaResponseDTO>> getByMesa(@PathVariable Integer idMesa) {
        logger.info("Obteniendo reservas de la mesa: " + idMesa);
        return ResponseEntity.ok(reservaService.findByMesa(idMesa));
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<ReservaResponseDTO>> getByCliente(@PathVariable Integer idCliente) {
        logger.info("Obteniendo reservas del cliente: " + idCliente);
        return ResponseEntity.ok(reservaService.findByCliente(idCliente));
    }

    @GetMapping("/activas")
    public ResponseEntity<List<ReservaResponseDTO>> getActivas() {
        logger.info("Obteniendo reservas activas");
        return ResponseEntity.ok(reservaService.findReservasActivas());
    }

    @PostMapping
    public ResponseEntity<ReservaResponseDTO> create(@Valid @RequestBody CreateReservaRequestDTO request) {
        logger.info("Creando nueva reserva");
        return new ResponseEntity<>(reservaService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateReservaRequestDTO request) {
        logger.info("Actualizando reserva con id: " + id);
        return ResponseEntity.ok(reservaService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        logger.info("Eliminando reserva con id: " + id);
        reservaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Integer id) {
        logger.info("Cancelando reserva con id: " + id);
        reservaService.cancelarReserva(id);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<Void> confirmar(@PathVariable Integer id) {
        logger.info("Confirmando reserva con id: " + id);
        reservaService.confirmarReserva(id);
        return ResponseEntity.ok().build();
    }
}