package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.request.trabajador.CreateTrabajadorRequestDTO;
import com.melateacafe.backend.dto.request.trabajador.UpdateTrabajadorRequestDTO;
import com.melateacafe.backend.dto.response.trabajador.TrabajadorResponseDTO;

import java.util.List;

public interface TrabajadorService {
    List<TrabajadorResponseDTO> findAll();
    TrabajadorResponseDTO findById(Integer id);
    TrabajadorResponseDTO create(CreateTrabajadorRequestDTO request);
    TrabajadorResponseDTO update(Integer id, UpdateTrabajadorRequestDTO request);
    void delete(Integer id);
    List<TrabajadorResponseDTO> findByCargo(Integer idCargo);
    List<TrabajadorResponseDTO> findByEstado(Boolean estado);
}
