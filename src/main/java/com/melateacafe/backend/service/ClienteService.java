package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.request.cliente.CreateClienteRequestDTO;
import com.melateacafe.backend.dto.request.cliente.UpdateClienteRequestDTO;
import com.melateacafe.backend.dto.response.cliente.ClienteResponseDTO;

import java.util.List;

public interface ClienteService {
    List<ClienteResponseDTO> findAll();
    ClienteResponseDTO findById(Integer id);
    ClienteResponseDTO create(CreateClienteRequestDTO request);
    ClienteResponseDTO update(Integer id, UpdateClienteRequestDTO request);
    void delete(Integer id);
    List<ClienteResponseDTO> findByEstado(Boolean estado);
    ClienteResponseDTO findByNumeroDocumento(String numeroDocumento);
    ClienteResponseDTO findByEmail(String email);
    List<ClienteResponseDTO> findByNombre(String nombre);
    List<ClienteResponseDTO> findClientesConReservas();
    List<ClienteResponseDTO> findClientesConPedidos();
}
