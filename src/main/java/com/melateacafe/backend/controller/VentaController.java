package com.melateacafe.backend.controller;

import com.melateacafe.backend.dto.request.venta.CreateVentaRequestDTO;
import com.melateacafe.backend.dto.response.venta.VentaResponseDTO;
import com.melateacafe.backend.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/venta")
public class VentaController {
    private final Logger logger = Logger.getLogger(VentaController.class.getName());

    @Autowired
    private VentaService ventaService;

    @GetMapping
    public ResponseEntity<List<VentaResponseDTO>> getAll() {
        logger.info("Obteniendo todas las ventas");
        return ResponseEntity.ok(ventaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaResponseDTO> getById(@PathVariable Integer id) {
        logger.info("Obteniendo venta con id: " + id);
        return ResponseEntity.ok(ventaService.findById(id));
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List<VentaResponseDTO>> getByFecha(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        logger.info("Obteniendo ventas de la fecha: " + fecha);
        return ResponseEntity.ok(ventaService.findByFecha(fecha));
    }

    @GetMapping("/fecha-rango")
    public ResponseEntity<List<VentaResponseDTO>> getByFechaRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        logger.info("Obteniendo ventas desde " + inicio + " hasta " + fin);
        return ResponseEntity.ok(ventaService.findByFechaRange(inicio, fin));
    }

    @GetMapping("/metodo-pago/{idMetodoPago}")
    public ResponseEntity<List<VentaResponseDTO>> getByMetodoPago(@PathVariable Integer idMetodoPago) {
        logger.info("Obteniendo ventas con método de pago: " + idMetodoPago);
        return ResponseEntity.ok(ventaService.findByMetodoPago(idMetodoPago));
    }

    @GetMapping("/tipo-comprobante/{idTipoComprobante}")
    public ResponseEntity<List<VentaResponseDTO>> getByTipoComprobante(@PathVariable Integer idTipoComprobante) {
        logger.info("Obteniendo ventas con tipo de comprobante: " + idTipoComprobante);
        return ResponseEntity.ok(ventaService.findByTipoComprobante(idTipoComprobante));
    }

    @GetMapping("/comprobante/{numeroComprobante}")
    public ResponseEntity<VentaResponseDTO> getByNumeroComprobante(@PathVariable String numeroComprobante) {
        logger.info("Obteniendo venta con número de comprobante: " + numeroComprobante);
        return ResponseEntity.ok(ventaService.findByNumeroComprobante(numeroComprobante));
    }

    @GetMapping("/total/fecha/{fecha}")
    public ResponseEntity<BigDecimal> getTotalByFecha(
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        logger.info("Obteniendo total de ventas de la fecha: " + fecha);
        return ResponseEntity.ok(ventaService.getTotalVentasByFecha(fecha));
    }

    @GetMapping("/total/fecha-rango")
    public ResponseEntity<BigDecimal> getTotalByFechaRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        logger.info("Obteniendo total de ventas desde " + inicio + " hasta " + fin);
        return ResponseEntity.ok(ventaService.getTotalVentasByFechaRange(inicio, fin));
    }

    @PostMapping
    public ResponseEntity<VentaResponseDTO> create(@Valid @RequestBody CreateVentaRequestDTO request) {
        logger.info("Creando nueva venta");
        return new ResponseEntity<>(ventaService.create(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        logger.info("Eliminando venta con id: " + id);
        ventaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}