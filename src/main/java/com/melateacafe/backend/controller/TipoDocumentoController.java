package com.melateacafe.backend.controller;

import com.melateacafe.backend.dto.response.tipo_documento.TipoDocumentoResponseDTO;
import com.melateacafe.backend.entity.TipoDocumento;
import com.melateacafe.backend.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/tipo-documento")
public class TipoDocumentoController {

    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @GetMapping
    public ResponseEntity<List<TipoDocumentoResponseDTO>> findAll() {
        return ResponseEntity.ok(tipoDocumentoService.findAll());
    }
}
