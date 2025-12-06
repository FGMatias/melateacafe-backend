package com.melateacafe.backend.controller;

import com.melateacafe.backend.dto.request.cliente.CreateClienteRequestDTO;
import com.melateacafe.backend.dto.request.cliente.UpdateClienteRequestDTO;
import com.melateacafe.backend.dto.response.cliente.ClienteResponseDTO;
import com.melateacafe.backend.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/cliente")
public class ClienteController {
    private final Logger logger = Logger.getLogger(ClienteController.class.getName());

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteResponseDTO>> getAll() {
        logger.info("Obteniendo todos los clientes");
        return ResponseEntity.ok(clienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> getById(@PathVariable Integer id) {
        logger.info("Obteniendo cliente con id: " + id);
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<ClienteResponseDTO>> getByEstado(@PathVariable Boolean estado) {
        logger.info("Obteniendo clientes con estado: " + estado);
        return ResponseEntity.ok(clienteService.findByEstado(estado));
    }

    @GetMapping("/documento/{numeroDocumento}")
    public ResponseEntity<ClienteResponseDTO> getByNumeroDocumento(@PathVariable String numeroDocumento) {
        logger.info("Obteniendo cliente con número de documento: " + numeroDocumento);
        return ResponseEntity.ok(clienteService.findByNumeroDocumento(numeroDocumento));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<ClienteResponseDTO> getByEmail(@PathVariable String email) {
        logger.info("Obteniendo cliente con email: " + email);
        return ResponseEntity.ok(clienteService.findByEmail(email));
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<ClienteResponseDTO>> findByNombre(@RequestParam(name = "q") String nombre) {
        logger.info("Buscando clientes con nombre: " + nombre);
        return ResponseEntity.ok(clienteService.findByNombre(nombre));
    }

    @GetMapping("/con-reservas")
    public ResponseEntity<List<ClienteResponseDTO>> getClientesConReservas() {
        logger.info("Obteniendo clientes con reservas");
        return ResponseEntity.ok(clienteService.findClientesConReservas());
    }

    @GetMapping("/con-pedidos")
    public ResponseEntity<List<ClienteResponseDTO>> getClientesConPedidos() {
        logger.info("Obteniendo clientes con pedidos");
        return ResponseEntity.ok(clienteService.findClientesConPedidos());
    }

    @PostMapping
    public ResponseEntity<ClienteResponseDTO> create(@Valid @RequestBody CreateClienteRequestDTO request) {
        logger.info("Creando nuevo cliente");
        return new ResponseEntity<>(clienteService.create(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResponseDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody UpdateClienteRequestDTO request) {
        logger.info("Actualizando cliente con id: " + id);
        return ResponseEntity.ok(clienteService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        logger.info("Eliminando cliente con id: " + id);
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }
}