package com.melateacafe.backend.controller;

import com.melateacafe.backend.dto.response.mesa.EstadoMesaResponseDTO;
import com.melateacafe.backend.dto.response.pedido.TipoPedidoResponseDTO;
import com.melateacafe.backend.service.TipoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/tipo-pedido")
public class TipoPedidoController {

    @Autowired
    private TipoPedidoService tipoPedidoService;

    @GetMapping
    public ResponseEntity<List<TipoPedidoResponseDTO>> findAll() {
        return ResponseEntity.ok(tipoPedidoService.findAll());
    }
}
