package com.melateacafe.backend.controller;

import com.melateacafe.backend.entity.CategoriaProducto;
import com.melateacafe.backend.service.CategoriaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/productos")
public class ProductosController {

    @Autowired
    private CategoriaProductoService categoriaProductoService;

    @GetMapping("/categorias")
    public List<CategoriaProducto> getAllCategorias() {
        return categoriaProductoService.findAll();
    }
}
