package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.TrabajadorDTO;
import com.melateacafe.backend.dto.request.trabajador.CreateTrabajadorRequestDTO;
import com.melateacafe.backend.dto.request.trabajador.UpdateTrabajadorRequestDTO;
import com.melateacafe.backend.dto.response.trabajador.TrabajadorResponseDTO;
import com.melateacafe.backend.entity.Trabajador;

import java.util.List;
import java.util.Optional;

public interface TrabajadorService {
    List<TrabajadorResponseDTO> findAll();
    TrabajadorResponseDTO findById(Integer id);
    TrabajadorResponseDTO create(CreateTrabajadorRequestDTO request);
    TrabajadorResponseDTO update(Integer id, UpdateTrabajadorRequestDTO request);
    void delete(Integer id);
    List<TrabajadorResponseDTO> findByCargo(Integer idCargo);
    List<TrabajadorResponseDTO> findByEstado(Boolean estado);
}
