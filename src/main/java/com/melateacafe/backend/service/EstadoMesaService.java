package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.response.mesa.EstadoMesaResponseDTO;

import java.util.List;

public interface EstadoMesaService {
    List<EstadoMesaResponseDTO> findAll();
}
