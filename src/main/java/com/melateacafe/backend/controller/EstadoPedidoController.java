package com.melateacafe.backend.controller;

import com.melateacafe.backend.dto.response.pedido.EstadoPedidoResponseDTO;
import com.melateacafe.backend.dto.response.tipo_documento.TipoDocumentoResponseDTO;
import com.melateacafe.backend.service.EstadoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/estado-pedido")
public class EstadoPedidoController {

    @Autowired
    private EstadoPedidoService estadoPedidoService;

    @GetMapping
    public ResponseEntity<List<EstadoPedidoResponseDTO>> findAll() {
        return ResponseEntity.ok(estadoPedidoService.findAll());
    }
}
