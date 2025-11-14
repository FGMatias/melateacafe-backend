package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.dto.TrabajadorDTO;
import com.melateacafe.backend.entity.Cargo;
import com.melateacafe.backend.entity.Trabajador;
import com.melateacafe.backend.repository.CargoRepository;
import com.melateacafe.backend.repository.TrabajadorRepository;
import com.melateacafe.backend.service.TrabajadorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private CargoRepository cargoRepository;

    @Override
    @Transactional
    public Trabajador save(TrabajadorDTO trabajadorDTO) {
        if (trabajadorRepository.existsByNumeroDocumento(trabajadorDTO.getNumeroDocumento())) {
            throw new EntityNotFoundException("El número de documento ya está registrado");
        }

        if (trabajadorRepository.existsByEmail(trabajadorDTO.getEmail())) {
            throw new EntityNotFoundException("El email ya está registrado");
        }

        Cargo cargo = cargoRepository.findById(trabajadorDTO.getIdCargo())
                .orElseThrow(() -> new EntityNotFoundException("Cargo no encontrado"));

        Trabajador trabajador = new Trabajador();
        trabajador.setCargo(cargo);
        trabajador.setNombres(trabajadorDTO.getNombres());
        trabajador.setApellidoPaterno(trabajadorDTO.getApellidoPaterno());
        trabajador.setApellidoMaterno(trabajadorDTO.getApellidoMaterno());
        trabajador.setEmail(trabajadorDTO.getEmail());
        trabajador.setTelefono(trabajadorDTO.getTelefono());
        trabajador.setNumeroDocumento(trabajadorDTO.getNumeroDocumento());
        trabajador.setFechaContratacion(trabajadorDTO.getFechaContratacion());
        trabajador.setEstado(trabajadorDTO.getEstado() != null ? trabajadorDTO.getEstado() : true);
        trabajador.setFechaCreacion(LocalDateTime.now());

        return trabajadorRepository.save(trabajador);
    }

    @Override
    public Trabajador update(Integer id, TrabajadorDTO trabajadorDTO) {
        Trabajador trabajador = trabajadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado"));

        if (!trabajador.getNumeroDocumento().equals(trabajadorDTO.getNumeroDocumento())) {
            if (trabajadorRepository.existsByNumeroDocumento(trabajadorDTO.getNumeroDocumento())) {
                throw new IllegalArgumentException("El número de documento ya está registrado");
            }

            trabajador.setNumeroDocumento(trabajadorDTO.getNumeroDocumento());
        }

        if (!trabajador.getEmail().equals(trabajadorDTO.getEmail())) {
            if (trabajadorRepository.existsByEmail(trabajadorDTO.getEmail())) {
                throw new IllegalArgumentException("El email ya está registrado");
            }

            trabajador.setEstado(trabajadorDTO.getEstado());
        }

        Cargo cargo = cargoRepository.findById(trabajadorDTO.getIdCargo())
                .orElseThrow(() -> new EntityNotFoundException("Cargo no encontrado"));

        trabajador.setCargo(cargo);
        trabajador.setNombres(trabajadorDTO.getNombres());
        trabajador.setApellidoPaterno(trabajadorDTO.getApellidoPaterno());
        trabajador.setApellidoMaterno(trabajadorDTO.getApellidoMaterno());
        trabajador.setTelefono(trabajadorDTO.getTelefono());
        trabajador.setFechaContratacion(trabajadorDTO.getFechaContratacion());
        trabajador.setEstado(trabajadorDTO.getEstado());

        return trabajadorRepository.save(trabajador);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Trabajador trabajador = trabajadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado"));

        trabajador.setEstado(false);
        trabajadorRepository.save(trabajador);
    }

    @Override
    public List<Trabajador> findAll() {
        return trabajadorRepository.findAll();
    }

    @Override
    public Trabajador findById(Integer id) {
        return trabajadorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado"));
    }

    @Override
    public List<Trabajador> findByCargo(Integer idCargo) {
        return trabajadorRepository.findByCargo_IdCargo(idCargo);
    }

    @Override
    public List<Trabajador> findByEstado(boolean estado) {
        return trabajadorRepository.findByEstado(estado);
    }
}
