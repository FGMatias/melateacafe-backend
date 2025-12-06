package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.request.reserva.CreateReservaRequestDTO;
import com.melateacafe.backend.dto.request.reserva.UpdateReservaRequestDTO;
import com.melateacafe.backend.dto.response.reserva.ReservaResponseDTO;

import java.time.LocalDate;
import java.util.List;

public interface ReservaService {
    List<ReservaResponseDTO> findAll();
    ReservaResponseDTO findById(Integer id);
    ReservaResponseDTO create(CreateReservaRequestDTO request);
    ReservaResponseDTO update(Integer id, UpdateReservaRequestDTO request);
    void delete(Integer id);
    List<ReservaResponseDTO> findByEstado(Boolean estado);
    List<ReservaResponseDTO> findByFecha(LocalDate fecha);
    List<ReservaResponseDTO> findByMesa(Integer idMesa);
    List<ReservaResponseDTO> findByCliente(Integer idCliente);
    List<ReservaResponseDTO> findReservasActivas();
    void cancelarReserva(Integer id);
    void confirmarReserva(Integer id);
}
