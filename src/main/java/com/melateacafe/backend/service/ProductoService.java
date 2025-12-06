package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.ProductoDTO;
import com.melateacafe.backend.dto.request.producto.CreateProductoRequestDTO;
import com.melateacafe.backend.dto.request.producto.UpdateProductoRequestDTO;
import com.melateacafe.backend.dto.response.producto.ProductoResponseDTO;
import com.melateacafe.backend.entity.Producto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductoService {
    List<ProductoResponseDTO> findAll();
    ProductoResponseDTO findById(Integer id);
    ProductoResponseDTO create(CreateProductoRequestDTO request);
    ProductoResponseDTO update(Integer id, UpdateProductoRequestDTO request);
    void delete(Integer id);
    List<ProductoResponseDTO> findActivos();
    List<ProductoResponseDTO> findByCategoria(Integer idCategoria);
    List<ProductoResponseDTO> findByNombre(String nombre);
    List<ProductoResponseDTO> findDestacados();
    List<ProductoResponseDTO> findWithStockBajo();
    List<ProductoResponseDTO> buscarProductos(Integer categoria, BigDecimal precioMin, BigDecimal precioMax, String nombre);
    List<ProductoResponseDTO> findByEstado(Boolean estado);
}
