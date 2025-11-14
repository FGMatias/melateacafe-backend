package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.dto.ProductoDTO;
import com.melateacafe.backend.entity.CategoriaProducto;
import com.melateacafe.backend.entity.Producto;
import com.melateacafe.backend.repository.CategoriaProductoRepository;
import com.melateacafe.backend.repository.ProductoRepository;
import com.melateacafe.backend.service.ProductoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    @Override
    @Transactional
    public Producto save(ProductoDTO productoDTO) {
        if (productoRepository.existsByNombre(productoDTO.getNombre())) {
            throw new IllegalArgumentException("Ya existe un producto con ese nombre");
        }

        CategoriaProducto categoria = categoriaProductoRepository.findById(productoDTO.getIdCategoria())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada"));

        Producto producto = new Producto();
        producto.setCategoriaProducto(categoria);
        producto.setNombre(productoDTO.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setImagenUrl(productoDTO.getImagenUrl());
        producto.setStockActual(productoDTO.getStockActual() != null
            ? productoDTO.getStockActual()
            : 0
        );
        producto.setStockMinimo(productoDTO.getStockMinimo() != null
            ? productoDTO.getStockMinimo()
            : 0
        );
        producto.setEstado(productoDTO.getEstado() != null
            ? productoDTO.getEstado()
            : true
        );
        producto.setFechaCreacion(LocalDateTime.now());

        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public Producto update(Integer id, ProductoDTO productoDTO) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        if (!producto.getNombre().equals(productoDTO.getNombre()) &&
                productoRepository.existsByNombre(productoDTO.getNombre())) {
            throw new IllegalArgumentException("Ya existe un producto con ese nombre");
        }

        if (!producto.getCategoriaProducto().getIdCategoriaProducto()
                .equals(productoDTO.getIdCategoria())) {
            CategoriaProducto categoria = categoriaProductoRepository
                    .findById(productoDTO.getIdCategoria())
                    .orElseThrow(() -> new EntityNotFoundException("Categoria no encontrada"));

            producto.setCategoriaProducto(categoria);
        }

        producto.setNombre(producto.getNombre());
        producto.setDescripcion(productoDTO.getDescripcion());
        producto.setPrecio(productoDTO.getPrecio());
        producto.setImagenUrl(productoDTO.getImagenUrl());
        producto.setStockActual(productoDTO.getStockActual());
        producto.setStockMinimo(productoDTO.getStockMinimo());
        producto.setEstado(productoDTO.getEstado());

        return productoRepository.save(producto);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        producto.setEstado(false);

        productoRepository.save(producto);
    }

    @Override
    public List<Producto> findAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto findById(Integer id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));
    }

    @Override
    public List<Producto> findByCategoria(Integer idCategoria) {
        return productoRepository.findByCategoriaProducto_IdCategoriaProducto(idCategoria);
    }

    @Override
    public List<Producto> findActivos() {
        return productoRepository.findByEstadoTrue();
    }

    @Override
    public List<Producto> findByNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public List<Producto> findByRangoPrecio(BigDecimal precioMin, BigDecimal precioMax) {
        return productoRepository.findByPrecioBetween(precioMin, precioMax);
    }

    @Override
    public List<Producto> buscarProductos(Integer idCategoria, BigDecimal precioMin, BigDecimal precioMax, String nombre) {
        return productoRepository.buscarProductos(idCategoria, precioMin, precioMax, nombre);
    }

    @Override
    public List<Producto> findDestacados() {
        return productoRepository.findProductosDestacados();
    }

    @Override
    public List<Producto> findConStockBajo() {
        return productoRepository.findProductosConStockBajo();
    }

    @Override
    @Transactional
    public boolean actualizarStock(Integer idProducto, Integer cantidad) {
        Producto producto = productoRepository.findById(idProducto)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado"));

        int nuevoStock = producto.getStockActual() + cantidad;

        if (nuevoStock < 0) {
            throw new IllegalArgumentException("Stock insuficiente");
        }

        producto.setStockActual(nuevoStock);
        productoRepository.save(producto);

        return true;
    }
}
