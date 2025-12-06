package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.response.venta.TipoComprobanteResponseDTO;

import java.util.List;

public interface TipoComprobanteService {
    List<TipoComprobanteResponseDTO> findAll();
}
