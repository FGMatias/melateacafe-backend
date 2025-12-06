package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.response.venta.MetodoPagoResponseDTO;

import java.util.List;

public interface MetodoPagoService {
    List<MetodoPagoResponseDTO> findAll();
}
