package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.dto.response.pedido.EstadoPedidoResponseDTO;
import com.melateacafe.backend.dto.response.venta.MetodoPagoResponseDTO;
import com.melateacafe.backend.entity.EstadoPedido;
import com.melateacafe.backend.entity.MetodoPago;
import com.melateacafe.backend.repository.MetodoPagoRepository;
import com.melateacafe.backend.service.MetodoPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MetodoPagoServiceImpl implements MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    @Override
    public List<MetodoPagoResponseDTO> findAll() {
        return metodoPagoRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private MetodoPagoResponseDTO convertToResponse(MetodoPago metodoPago) {
        MetodoPagoResponseDTO response = new MetodoPagoResponseDTO();
        response.setIdMetodoPago(metodoPago.getIdMetodoPago());
        response.setNombre(metodoPago.getNombre());
        return response;
    }
}
