package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.dto.request.mesa.CreateMesaRequestDTO;
import com.melateacafe.backend.dto.request.mesa.UpdateMesaRequestDTO;
import com.melateacafe.backend.dto.response.mesa.EstadoMesaResponseDTO;
import com.melateacafe.backend.dto.response.mesa.MesaResponseDTO;
import com.melateacafe.backend.entity.EstadoMesa;
import com.melateacafe.backend.entity.Mesa;
import com.melateacafe.backend.repository.EstadoMesaRepository;
import com.melateacafe.backend.repository.MesaRepository;
import com.melateacafe.backend.service.MesaService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MesaServiceImpl implements MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private EstadoMesaRepository estadoMesaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<MesaResponseDTO> findAll() {
        return mesaRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public MesaResponseDTO findById(Integer id) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mesa no encontrada con id: " + id));
        return convertToResponse(mesa);
    }

    @Override
    @Transactional
    public MesaResponseDTO create(CreateMesaRequestDTO request) {
        EstadoMesa estadoMesa = estadoMesaRepository.findById(request.getIdEstado())
                .orElseThrow(() -> new EntityNotFoundException("Estado de mesa no encontrado con id: " + request.getIdEstado()));

        if (mesaRepository.existsByNumero(request.getNumero())) {
            throw new IllegalArgumentException("Ya existe una mesa con el número: " + request.getNumero());
        }

        Mesa mesa = new Mesa();
        mesa.setEstadoMesa(estadoMesa);
        mesa.setNumero(request.getNumero());
        mesa.setCapacidad(request.getCapacidad());
        mesa.setEstado(request.getEstado() != null ? request.getEstado() : true);

        Mesa saved = mesaRepository.save(mesa);
        return convertToResponse(saved);
    }

    @Override
    @Transactional
    public MesaResponseDTO update(Integer id, UpdateMesaRequestDTO request) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mesa no encontrada con id: " + id));

        EstadoMesa estadoMesa = estadoMesaRepository.findById(request.getIdEstado())
                .orElseThrow(() -> new EntityNotFoundException("Estado de mesa no encontrado con id: " + request.getIdEstado()));

        if (!mesa.getNumero().equals(request.getNumero())) {
            if (mesaRepository.existsByNumero(request.getNumero())) {
                throw new IllegalArgumentException("Ya existe una mesa con el número: " + request.getNumero());
            }
        }

        mesa.setEstadoMesa(estadoMesa);
        mesa.setNumero(request.getNumero());
        mesa.setCapacidad(request.getCapacidad());
        mesa.setEstado(request.getEstado() != null ? request.getEstado() : mesa.getEstado());

        Mesa updated = mesaRepository.save(mesa);
        return convertToResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Mesa mesa = mesaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Mesa no encontrada con id: " + id));

        if (mesaRepository.hasReservasActivas(id)) {
            throw new IllegalStateException("No se puede eliminar la mesa porque tiene reservas activas");
        }

        mesaRepository.delete(mesa);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MesaResponseDTO> findByEstado(Boolean estado) {
        return mesaRepository.findByEstado(estado).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MesaResponseDTO> findByEstadoMesa(Integer idEstadoMesa) {
        return mesaRepository.findByEstadoMesa_IdEstadoMesa(idEstadoMesa).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MesaResponseDTO> findByCapacidad(Integer capacidad) {
        return mesaRepository.findByCapacidad(capacidad).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<MesaResponseDTO> findDisponibles() {
        return mesaRepository.findByEstadoMesa_IdEstadoMesaAndEstado(1, true).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private MesaResponseDTO convertToResponse(Mesa mesa) {
        MesaResponseDTO response = new MesaResponseDTO();
        response.setIdMesa(mesa.getIdMesa());
        response.setEstadoMesa(convertEstadoMesaToResponse(mesa.getEstadoMesa()));
        response.setNumero(mesa.getNumero());
        response.setCapacidad(mesa.getCapacidad());
        response.setEstado(mesa.isEstado());
        return response;
    }

    private EstadoMesaResponseDTO convertEstadoMesaToResponse(EstadoMesa estadoMesa) {
        EstadoMesaResponseDTO response = new EstadoMesaResponseDTO();
        response.setIdEstadoMesa(estadoMesa.getIdEstadoMesa());
        response.setNombre(estadoMesa.getNombre());
        return response;
    }
}
