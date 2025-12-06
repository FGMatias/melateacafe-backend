package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.response.pedido.EstadoPedidoResponseDTO;

import java.util.List;

public interface EstadoPedidoService {
    List<EstadoPedidoResponseDTO> findAll();
}
