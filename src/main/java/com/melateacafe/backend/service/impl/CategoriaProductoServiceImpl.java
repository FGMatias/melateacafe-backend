package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.entity.CategoriaProducto;
import com.melateacafe.backend.repository.CategoriaProductoRepository;
import com.melateacafe.backend.service.CategoriaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaProductoServiceImpl implements CategoriaProductoService {

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    @Override
    public List<CategoriaProducto> findAll() {
        return categoriaProductoRepository.findAll();
    }
}
