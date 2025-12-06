package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.request.categoria.CreateCategoriaRequestDTO;
import com.melateacafe.backend.dto.request.categoria.UpdateCategoriaRequestDTO;
import com.melateacafe.backend.dto.response.categoria.CategoriaResponseDTO;
import com.melateacafe.backend.entity.CategoriaProducto;

import java.util.List;

public interface CategoriaProductoService {
    List<CategoriaResponseDTO> findAll();
    CategoriaResponseDTO findById(Integer id);
    CategoriaResponseDTO create(CreateCategoriaRequestDTO request);
    CategoriaResponseDTO update(Integer id, UpdateCategoriaRequestDTO request);
    void delete(Integer id);
    List<CategoriaResponseDTO> findByEstado(Boolean estado);
}
