package com.melateacafe.backend.service;

import com.melateacafe.backend.entity.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> findAll();
}
