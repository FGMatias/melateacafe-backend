package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.dto.response.categoria.CategoriaResponseDTO;
import com.melateacafe.backend.dto.response.mesa.EstadoMesaResponseDTO;
import com.melateacafe.backend.entity.CategoriaProducto;
import com.melateacafe.backend.entity.EstadoMesa;
import com.melateacafe.backend.repository.EstadoMesaRepository;
import com.melateacafe.backend.service.EstadoMesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoMesaServiceImpl implements EstadoMesaService {

    @Autowired
    private EstadoMesaRepository estadoMesaRepository;

    @Override
    public List<EstadoMesaResponseDTO> findAll() {
        return estadoMesaRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private EstadoMesaResponseDTO convertToResponse(EstadoMesa estadoMesa) {
        EstadoMesaResponseDTO response = new EstadoMesaResponseDTO();
        response.setIdEstadoMesa(estadoMesa.getIdEstadoMesa());
        response.setNombre(estadoMesa.getNombre());
        return response;
    }
}
