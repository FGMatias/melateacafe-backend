package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.request.mesa.CreateMesaRequestDTO;
import com.melateacafe.backend.dto.request.mesa.UpdateMesaRequestDTO;
import com.melateacafe.backend.dto.response.mesa.MesaResponseDTO;

import java.util.List;

public interface MesaService {
    List<MesaResponseDTO> findAll();
    MesaResponseDTO findById(Integer id);
    MesaResponseDTO create(CreateMesaRequestDTO request);
    MesaResponseDTO update(Integer id, UpdateMesaRequestDTO request);
    void delete(Integer id);
    List<MesaResponseDTO> findByEstado(Boolean estado);
    List<MesaResponseDTO> findByEstadoMesa(Integer idEstadoMesa);
    List<MesaResponseDTO> findByCapacidad(Integer capacidad);
    List<MesaResponseDTO> findDisponibles();
}
