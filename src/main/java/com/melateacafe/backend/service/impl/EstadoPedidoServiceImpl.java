package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.dto.response.pedido.EstadoPedidoResponseDTO;
import com.melateacafe.backend.dto.response.tipo_documento.TipoDocumentoResponseDTO;
import com.melateacafe.backend.entity.EstadoPedido;
import com.melateacafe.backend.entity.TipoDocumento;
import com.melateacafe.backend.repository.EstadoPedidoRepository;
import com.melateacafe.backend.service.EstadoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstadoPedidoServiceImpl implements EstadoPedidoService {

    @Autowired
    private EstadoPedidoRepository estadoPedidoRepository;

    @Override
    public List<EstadoPedidoResponseDTO> findAll() {
        return estadoPedidoRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private EstadoPedidoResponseDTO convertToResponse(EstadoPedido estadoPedido) {
        EstadoPedidoResponseDTO response = new EstadoPedidoResponseDTO();
        response.setIdEstadoPedido(estadoPedido.getIdEstadoPedido());
        response.setNombre(estadoPedido.getNombre());
        return response;
    }
}
