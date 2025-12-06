package com.melateacafe.backend.controller;

import com.melateacafe.backend.dto.response.mesa.EstadoMesaResponseDTO;
import com.melateacafe.backend.service.EstadoMesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/estado-mesa")
public class EstadoMesaController {

    @Autowired
    private EstadoMesaService estadoMesaService;

    @GetMapping
    public ResponseEntity<List<EstadoMesaResponseDTO>> findAll() {
        return ResponseEntity.ok(estadoMesaService.findAll());
    }
}
