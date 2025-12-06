package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.dto.response.pedido.TipoPedidoResponseDTO;
import com.melateacafe.backend.dto.response.tipo_documento.TipoDocumentoResponseDTO;
import com.melateacafe.backend.entity.TipoDocumento;
import com.melateacafe.backend.entity.TipoPedido;
import com.melateacafe.backend.repository.TipoPedidoRepository;
import com.melateacafe.backend.service.TipoPedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoPedidoServiceImpl implements TipoPedidoService {

    @Autowired
    private TipoPedidoRepository tipoPedidoRepository;

    @Override
    public List<TipoPedidoResponseDTO> findAll() {
        return tipoPedidoRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private TipoPedidoResponseDTO convertToResponse(TipoPedido tipoPedido) {
        TipoPedidoResponseDTO response = new TipoPedidoResponseDTO();
        response.setIdTipoPedido(tipoPedido.getIdTipoPedido());
        response.setNombre(tipoPedido.getNombre());
        return response;
    }
}
