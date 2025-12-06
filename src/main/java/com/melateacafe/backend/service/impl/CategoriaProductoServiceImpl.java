package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.dto.request.categoria.CreateCategoriaRequestDTO;
import com.melateacafe.backend.dto.request.categoria.UpdateCategoriaRequestDTO;
import com.melateacafe.backend.dto.response.categoria.CategoriaResponseDTO;
import com.melateacafe.backend.entity.CategoriaProducto;
import com.melateacafe.backend.repository.CategoriaProductoRepository;
import com.melateacafe.backend.service.CategoriaProductoService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaProductoServiceImpl implements CategoriaProductoService {

    @Autowired
    private CategoriaProductoRepository categoriaProductoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> findAll() {
        return categoriaProductoRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public CategoriaResponseDTO findById(Integer id) {
        CategoriaProducto categoria = categoriaProductoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con id: " + id));
        return convertToResponse(categoria);
    }

    @Override
    @Transactional
    public CategoriaResponseDTO create(CreateCategoriaRequestDTO request) {
        if (categoriaProductoRepository.existsByNombre(request.getNombre())) {
            throw new IllegalArgumentException("Ya existe una categoría con el nombre: " + request.getNombre());
        }

        CategoriaProducto categoria = new CategoriaProducto();
        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());
        categoria.setEstado(request.getEstado() != null ? request.getEstado() : true);

        CategoriaProducto saved = categoriaProductoRepository.save(categoria);
        return convertToResponse(saved);
    }

    @Override
    @Transactional
    public CategoriaResponseDTO update(Integer id, UpdateCategoriaRequestDTO request) {
        CategoriaProducto categoria = categoriaProductoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con id: " + id));

        if (!categoria.getNombre().equals(request.getNombre())) {
            if (categoriaProductoRepository.existsByNombre(request.getNombre())) {
                throw new IllegalArgumentException("Ya existe una categoría con el nombre: " + request.getNombre());
            }
        }

        categoria.setNombre(request.getNombre());
        categoria.setDescripcion(request.getDescripcion());
        categoria.setEstado(request.getEstado() != null ? request.getEstado() : categoria.isEstado());

        CategoriaProducto updated = categoriaProductoRepository.save(categoria);
        return convertToResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        CategoriaProducto categoria = categoriaProductoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoría no encontrada con id: " + id));

        if (categoriaProductoRepository.hasProductos(id)) {
            throw new IllegalStateException("No se puede eliminar la categoría porque tiene productos asociados");
        }

        categoriaProductoRepository.delete(categoria);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoriaResponseDTO> findByEstado(Boolean estado) {
        return categoriaProductoRepository.findByEstado(estado).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private CategoriaResponseDTO convertToResponse(CategoriaProducto categoria) {
        CategoriaResponseDTO response = new CategoriaResponseDTO();
        response.setIdCategoriaProducto(categoria.getIdCategoriaProducto());
        response.setNombre(categoria.getNombre());
        response.setDescripcion(categoria.getDescripcion());
        response.setEstado(categoria.isEstado());
        return response;
    }
}
