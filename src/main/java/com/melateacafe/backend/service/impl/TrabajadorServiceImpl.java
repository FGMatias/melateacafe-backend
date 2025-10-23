package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.entity.Trabajador;
import com.melateacafe.backend.repository.TrabajadorRepository;
import com.melateacafe.backend.service.TrabajadorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrabajadorServiceImpl implements TrabajadorService {
    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Override
    public Trabajador save(Trabajador trabajador) {
        return null;
    }

    @Override
    public Trabajador update(Trabajador trabajador) {
        return null;
    }

    @Override
    public void delete(Integer id) {

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
}
