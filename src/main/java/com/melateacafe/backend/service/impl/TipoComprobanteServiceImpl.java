package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.dto.response.mesa.EstadoMesaResponseDTO;
import com.melateacafe.backend.dto.response.venta.TipoComprobanteResponseDTO;
import com.melateacafe.backend.entity.EstadoMesa;
import com.melateacafe.backend.entity.TipoComprobante;
import com.melateacafe.backend.repository.TipoComprobanteRepository;
import com.melateacafe.backend.service.TipoComprobanteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoComprobanteServiceImpl implements TipoComprobanteService {

    @Autowired
    private TipoComprobanteRepository tipoComprobanteRepository;

    @Override
    public List<TipoComprobanteResponseDTO> findAll() {
        return tipoComprobanteRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private TipoComprobanteResponseDTO convertToResponse(TipoComprobante tipoComprobante) {
        TipoComprobanteResponseDTO response = new TipoComprobanteResponseDTO();
        response.setIdTipoComprobante(tipoComprobante.getIdTipoComprobante());
        response.setNombre(tipoComprobante.getNombre());
        response.setCorrelativo(tipoComprobante.getCorrelativo());
        response.setSerie(tipoComprobante.getSerie());
        response.setFormatoSerie(tipoComprobante.getFormatoSerie());
        response.setCodigo(tipoComprobante.getCodigo());
        return response;
    }
}
