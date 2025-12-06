package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.request.venta.CreateVentaRequestDTO;
import com.melateacafe.backend.dto.response.venta.VentaResponseDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface VentaService {
    List<VentaResponseDTO> findAll();
    VentaResponseDTO findById(Integer id);
    VentaResponseDTO create(CreateVentaRequestDTO request);
    void delete(Integer id);
    List<VentaResponseDTO> findByFecha(LocalDate fecha);
    List<VentaResponseDTO> findByFechaRange(LocalDate inicio, LocalDate fin);
    List<VentaResponseDTO> findByMetodoPago(Integer idMetodoPago);
    List<VentaResponseDTO> findByTipoComprobante(Integer idTipoComprobante);
    VentaResponseDTO findByNumeroComprobante(String numeroComprobante);
    BigDecimal getTotalVentasByFecha(LocalDate fecha);
    BigDecimal getTotalVentasByFechaRange(LocalDate inicio, LocalDate fin);
}
