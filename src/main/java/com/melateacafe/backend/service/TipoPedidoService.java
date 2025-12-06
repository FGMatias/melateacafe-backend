package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.response.pedido.TipoPedidoResponseDTO;

import java.util.List;

public interface TipoPedidoService {
    List<TipoPedidoResponseDTO> findAll();
}
