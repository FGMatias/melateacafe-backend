package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.request.pedido.CreatePedidoRequestDTO;
import com.melateacafe.backend.dto.request.pedido.UpdatePedidoRequestDTO;
import com.melateacafe.backend.dto.response.pedido.PedidoResponseDTO;

import java.util.List;

public interface PedidoService {
    List<PedidoResponseDTO> findAll();
    PedidoResponseDTO findById(Integer id);
    PedidoResponseDTO create(CreatePedidoRequestDTO request);
    PedidoResponseDTO update(Integer id, UpdatePedidoRequestDTO request);
    void delete(Integer id);
    List<PedidoResponseDTO> findByEstado(Integer idEstado);
    List<PedidoResponseDTO> findByTipoPedido(Integer idTipoPedido);
    List<PedidoResponseDTO> findByCliente(Integer idCliente);
    List<PedidoResponseDTO> findByMesa(Integer idMesa);
    void cancelarPedido(Integer id);
}
