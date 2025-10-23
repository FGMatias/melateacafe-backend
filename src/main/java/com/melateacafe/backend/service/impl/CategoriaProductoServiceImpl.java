package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.entity.CategoriaProducto;
import com.melateacafe.backend.repository.CategoriaProductoRepository;
import com.melateacafe.backend.service.CategoriaProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class CategoriaProductoServiceImpl implements CategoriaProductoService {
    private final Logger logger = Logger.getLogger(CategoriaProductoServiceImpl.class.getName());

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    @Override
    public List<CategoriaProducto> findAll() {
        logger.info("Lista de categorias desde service impl");
        return categoriaProductoRepository.findAll();
    }
}
