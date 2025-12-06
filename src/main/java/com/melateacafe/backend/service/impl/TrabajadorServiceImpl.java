package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.dto.request.trabajador.CreateTrabajadorRequestDTO;
import com.melateacafe.backend.dto.request.trabajador.UpdateTrabajadorRequestDTO;
import com.melateacafe.backend.dto.response.cargo.CargoResponseDTO;
import com.melateacafe.backend.dto.response.trabajador.TrabajadorResponseDTO;
import com.melateacafe.backend.entity.Cargo;
import com.melateacafe.backend.entity.Trabajador;
import com.melateacafe.backend.repository.CargoRepository;
import com.melateacafe.backend.repository.TrabajadorRepository;
import com.melateacafe.backend.repository.UsuarioRepository;
import com.melateacafe.backend.service.TrabajadorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public List<TrabajadorResponseDTO> findAll() {
        return trabajadorRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TrabajadorResponseDTO findById(Integer id) {
        Trabajador trabajador = trabajadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado con ID: " + id));
        return convertToResponse(trabajador);
    }

    @Override
    @Transactional
    public TrabajadorResponseDTO create(CreateTrabajadorRequestDTO request) {
        Cargo cargo = cargoRepository.findById(request.getIdCargo())
                .orElseThrow(() -> new EntityNotFoundException("Cargo no encontrado"));

        if (trabajadorRepository.existsByNumeroDocumento(request.getNumeroDocumento())) {
            throw new IllegalArgumentException("Ya existe un trabajador con el DNI: " + request.getNumeroDocumento());
        }

        if (trabajadorRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Ya existe un trabajador con el email: " + request.getEmail());
        }

        Trabajador trabajador = new Trabajador();
        trabajador.setCargo(cargo);
        trabajador.setNombres(request.getNombres());
        trabajador.setApellidoPaterno(request.getApellidoPaterno());
        trabajador.setApellidoMaterno(request.getApellidoMaterno());
        trabajador.setEmail(request.getEmail());
        trabajador.setTelefono(request.getTelefono());
        trabajador.setNumeroDocumento(request.getNumeroDocumento());
        trabajador.setFechaContratacion(request.getFechaContratacion());
        trabajador.setEstado(request.getEstado());
        trabajador.setFechaCreacion(LocalDateTime.now());

        Trabajador saved = trabajadorRepository.save(trabajador);
        return convertToResponse(saved);
    }

    @Override
    @Transactional
    public TrabajadorResponseDTO update(Integer id, UpdateTrabajadorRequestDTO request) {
        Trabajador trabajador = trabajadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado"));

        Cargo cargo = cargoRepository.findById(request.getIdCargo())
                .orElseThrow(() -> new EntityNotFoundException("Cargo no encontrado"));

        if (!trabajador.getNumeroDocumento().equals(request.getNumeroDocumento())) {
            if (trabajadorRepository.existsByNumeroDocumento(request.getNumeroDocumento())) {
                throw new IllegalArgumentException("El DNI ya está en uso");
            }
        }

        if (!trabajador.getEmail().equals(request.getEmail())) {
            if (trabajadorRepository.existsByEmail(request.getEmail())) {
                throw new IllegalArgumentException("El email ya está en uso");
            }
        }

        trabajador.setCargo(cargo);
        trabajador.setNombres(request.getNombres());
        trabajador.setApellidoPaterno(request.getApellidoPaterno());
        trabajador.setApellidoMaterno(request.getApellidoMaterno());
        trabajador.setEmail(request.getEmail());
        trabajador.setTelefono(request.getTelefono());
        trabajador.setNumeroDocumento(request.getNumeroDocumento());
        trabajador.setFechaContratacion(request.getFechaContratacion());
        trabajador.setEstado(request.getEstado());

        Trabajador updated = trabajadorRepository.save(trabajador);
        return convertToResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Trabajador trabajador = trabajadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado"));

        if (usuarioRepository.existsByTrabajador_IdTrabajador(id)) {
            throw new IllegalStateException("No se puede eliminar un trabajador que tiene un usuario asociado");
        }

        trabajadorRepository.delete(trabajador);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrabajadorResponseDTO> findByCargo(Integer idCargo) {
        return trabajadorRepository.findByCargo_IdCargo(idCargo).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<TrabajadorResponseDTO> findByEstado(Boolean estado) {
        return trabajadorRepository.findByEstado(estado).stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    private TrabajadorResponseDTO convertToResponse(Trabajador trabajador) {
        TrabajadorResponseDTO response = new TrabajadorResponseDTO();
        response.setIdTrabajador(trabajador.getIdTrabajador());
        response.setCargo(convertCargoToResponse(trabajador.getCargo()));
        response.setNombres(trabajador.getNombres());
        response.setApellidoPaterno(trabajador.getApellidoPaterno());
        response.setApellidoMaterno(trabajador.getApellidoMaterno());
        response.setEmail(trabajador.getEmail());
        response.setTelefono(trabajador.getTelefono());
        response.setNumeroDocumento(trabajador.getNumeroDocumento());
        response.setFechaContratacion(trabajador.getFechaContratacion());
        response.setEstado(trabajador.isEstado());
        response.setFechaCreacion(trabajador.getFechaCreacion());

        response.setTieneUsuario(
                usuarioRepository.existsByTrabajador_IdTrabajador(trabajador.getIdTrabajador())
        );

        return response;
    }

    private CargoResponseDTO convertCargoToResponse(Cargo cargo) {
        CargoResponseDTO response = new CargoResponseDTO();
        response.setIdCargo(cargo.getIdCargo());
        response.setNombre(cargo.getNombre());
        response.setSalarioBase(cargo.getSalarioBase());
        response.setEstado(cargo.isEstado());
        response.setFechaCreacion(cargo.getFechaCreacion());
        return response;
    }
}
