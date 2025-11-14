package com.melateacafe.backend.controller;

import com.melateacafe.backend.entity.CategoriaProducto;
import com.melateacafe.backend.service.CategoriaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/categoria")
public class CategoriaController {
    private final Logger logger = Logger.getLogger(CategoriaController.class.getName());

    @Autowired
    private CategoriaProductoService categoriaProductoService;

    @GetMapping()
    public ResponseEntity<List<CategoriaProducto>> getAll() {
        logger.info("Obteniendo todas las categorias");
        List<CategoriaProducto> categorias = categoriaProductoService.findAll();
        return ResponseEntity.ok(categorias);
    }
}
