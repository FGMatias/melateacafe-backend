package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.ProductoDTO;
import com.melateacafe.backend.entity.Producto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductoService {
    Producto save(ProductoDTO productoDTO);
    Producto update(Integer id, ProductoDTO productoDTO);
    void delete(Integer id);
    List<Producto> findAll();
    Producto findById(Integer id);
    List<Producto> findByCategoria(Integer idCategoria);
    List<Producto> findActivos();
    List<Producto> findByNombre(String nombre);
    List<Producto> findByRangoPrecio(BigDecimal precioMin, BigDecimal precioMax);
    List<Producto> buscarProductos(Integer idCategoria, BigDecimal precioMin, BigDecimal precioMax, String nombre);
    List<Producto> findDestacados();
    List<Producto> findConStockBajo();
    boolean actualizarStock(Integer idProducto, Integer cantidad);
}
