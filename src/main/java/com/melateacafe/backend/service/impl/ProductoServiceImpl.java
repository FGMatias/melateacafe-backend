package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.dto.ProductoDTO;
import com.melateacafe.backend.dto.request.producto.CreateProductoRequestDTO;
import com.melateacafe.backend.dto.request.producto.UpdateProductoRequestDTO;
import com.melateacafe.backend.dto.response.categoria.CategoriaResponseDTO;
import com.melateacafe.backend.dto.response.producto.ProductoResponseDTO;
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
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> findAll() {
        return productoRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ProductoResponseDTO findById(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + id));
        return convertToResponse(producto);
    }

    @Override
    @Transactional
    public ProductoResponseDTO create(CreateProductoRequestDTO request) {
        CategoriaProducto categoria = categoriaProductoRepository.findById(request.getIdCategoriaProducto())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con id: " + request.getIdCategoriaProducto()));

        if (productoRepository.existsByNombre(request.getNombre())) {
            throw new IllegalArgumentException("Ya existe un producto con el nombre: " + request.getNombre());
        }

        Producto producto = new Producto();
        producto.setCategoriaProducto(categoria);
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setImagenUrl(request.getImagenUrl());
        producto.setEstado(request.getEstado() != null ? request.getEstado() : true);

        Producto saved = productoRepository.save(producto);
        return convertToResponse(saved);
    }

    @Override
    @Transactional
    public ProductoResponseDTO update(Integer id, UpdateProductoRequestDTO request) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + id));

        CategoriaProducto categoria = categoriaProductoRepository.findById(request.getIdCategoriaProducto())
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con id: " + request.getIdCategoriaProducto()));

        if (!producto.getNombre().equals(request.getNombre())) {
            if (productoRepository.existsByNombre(request.getNombre())) {
                throw new IllegalArgumentException("Ya existe un producto con el nombre: " + request.getNombre());
            }
        }

        producto.setCategoriaProducto(categoria);
        producto.setNombre(request.getNombre());
        producto.setDescripcion(request.getDescripcion());
        producto.setPrecio(request.getPrecio());
        producto.setImagenUrl(request.getImagenUrl());
        producto.setEstado(request.getEstado() != null ? request.getEstado() : producto.isEstado());

        Producto updated = productoRepository.save(producto);
        return convertToResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Producto no encontrado con id: " + id));
        productoRepository.delete(producto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> findActivos() {
        return productoRepository.findByEstado(true).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> findByCategoria(Integer idCategoria) {
        return productoRepository.findByCategoriaProducto_IdCategoriaProducto(idCategoria).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> findByNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> findDestacados() {
        return productoRepository.findProductosDestacados().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> findWithStockBajo() {
        return productoRepository.findProductosConStockBajo().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> buscarProductos(Integer categoria, BigDecimal precioMin, BigDecimal precioMax, String nombre) {
        return productoRepository.buscarProductos(categoria, precioMin, precioMax, nombre).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductoResponseDTO> findByEstado(Boolean estado) {
        return productoRepository.findByEstado(estado).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private ProductoResponseDTO convertToResponse(Producto producto) {
        ProductoResponseDTO response = new ProductoResponseDTO();
        response.setIdProducto(producto.getIdProducto());
        response.setCategoriaProducto(convertCategoriaToResponse(producto.getCategoriaProducto()));
        response.setNombre(producto.getNombre());
        response.setDescripcion(producto.getDescripcion());
        response.setPrecio(producto.getPrecio());
        response.setImagenUrl(producto.getImagenUrl());
        response.setEstado(producto.isEstado());
        return response;
    }

    private CategoriaResponseDTO convertCategoriaToResponse(CategoriaProducto categoria) {
        CategoriaResponseDTO response = new CategoriaResponseDTO();
        response.setIdCategoriaProducto(categoria.getIdCategoriaProducto());
        response.setNombre(categoria.getNombre());
        response.setDescripcion(categoria.getDescripcion());
        response.setEstado(categoria.isEstado());
        return response;
    }
}
