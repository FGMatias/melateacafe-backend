package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.dto.response.tipo_documento.TipoDocumentoResponseDTO;
import com.melateacafe.backend.dto.response.venta.TipoComprobanteResponseDTO;
import com.melateacafe.backend.entity.TipoComprobante;
import com.melateacafe.backend.entity.TipoDocumento;
import com.melateacafe.backend.repository.TipoDocumentoRepository;
import com.melateacafe.backend.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoDocumentoServiceImpl implements TipoDocumentoService {

    @Autowired
    private TipoDocumentoRepository tipoDocumentoRepository;

    @Override
    public List<TipoDocumentoResponseDTO> findAll() {
        return tipoDocumentoRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private TipoDocumentoResponseDTO convertToResponse(TipoDocumento tipoDocumento) {
        TipoDocumentoResponseDTO response = new TipoDocumentoResponseDTO();
        response.setIdTipoDocumento(tipoDocumento.getIdTipoDocumento());
        response.setNombre(tipoDocumento.getNombre());
        return response;
    }
}
