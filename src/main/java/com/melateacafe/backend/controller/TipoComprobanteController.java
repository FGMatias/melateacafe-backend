package com.melateacafe.backend.controller;

import com.melateacafe.backend.dto.response.venta.TipoComprobanteResponseDTO;
import com.melateacafe.backend.service.TipoComprobanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/tipo-comprobante")
public class TipoComprobanteController {

    @Autowired
    private TipoComprobanteService tipoComprobanteService;

    @GetMapping
    public ResponseEntity<List<TipoComprobanteResponseDTO>> findAll() {
        return ResponseEntity.ok(tipoComprobanteService.findAll());
    }
}
