package com.melateacafe.backend.controller;

import com.melateacafe.backend.dto.request.pedido.CreatePedidoRequestDTO;
import com.melateacafe.backend.dto.request.pedido.UpdatePedidoRequestDTO;
import com.melateacafe.backend.dto.response.pedido.PedidoResponseDTO;
import com.melateacafe.backend.service.PedidoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/pedido")
public class PedidoController {
    private final Logger logger = Logger.getLogger(PedidoController.class.getName());

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoResponseDTO>> getAll() {
        logger.info("Obteniendo todos los pedidos");
        return ResponseEntity.ok(pedidoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> getById(@PathVariable Integer id) {
        logger.info("Obteniendo pedido con id: " + id);
        return ResponseEntity.ok(pedidoService.findById(id));
    }

    @GetMapping("/estado/{idEstado}")
    public ResponseEntity<List<PedidoResponseDTO>> getByEstado(@PathVariable Integer idEstado) {
        logger.info("Obteniendo pedidos con estado: " + idEstado);
        return ResponseEntity.ok(pedidoService.findByEstado(idEstado));
    }

    @GetMapping("/tipo/{idTipoPedido}")
    public ResponseEntity<List<PedidoResponseDTO>> getByTipoPedido(@PathVariable Integer idTipoPedido) {
        logger.info("Obteniendo pedidos con tipo: " + idTipoPedido);
        return ResponseEntity.ok(pedidoService.findByTipoPedido(idTipoPedido));
    }

    @GetMapping("/cliente/{idCliente}")
    public ResponseEntity<List<PedidoResponseDTO>> getByCliente(@PathVariable Integer idCliente) {
        logger.info("Obteniendo pedidos del cliente: " + idCliente);
        return ResponseEntity.ok(pedidoService.findByCliente(idCliente));
    }

    @GetMapping("/mesa/{idMesa}")
    public ResponseEntity<List<PedidoResponseDTO>> getByMesa(@PathVariable Integer idMesa) {
        logger.info("Obteniendo pedidos de la mesa: " + idMesa);
        return ResponseEntity.ok(pedidoService.findByMesa(idMesa));
    }

    @PostMapping
    public ResponseEntity<PedidoResponseDTO> create(@Valid @RequestBody CreatePedidoRequestDTO request) {
        logger.info("Creando nuevo pedido");
        return new ResponseEntity<>(pedidoService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody UpdatePedidoRequestDTO request) {
        logger.info("Actualizando pedido con id: " + id);
        return ResponseEntity.ok(pedidoService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        logger.info("Eliminando pedido con id: " + id);
        pedidoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<Void> cancelar(@PathVariable Integer id) {
        logger.info("Cancelando pedido con id: " + id);
        pedidoService.cancelarPedido(id);
        return ResponseEntity.ok().build();
    }
}