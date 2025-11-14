package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.TrabajadorDTO;
import com.melateacafe.backend.entity.Trabajador;

import java.util.List;
import java.util.Optional;

public interface TrabajadorService {
    Trabajador save(TrabajadorDTO trabajadorDTO);
    Trabajador update(Integer id, TrabajadorDTO trabajadorDTO);
    void delete(Integer id);
    List<Trabajador> findAll();
    Trabajador findById(Integer id);
    List<Trabajador> findByCargo(Integer idCargo);
    List<Trabajador> findByEstado(boolean estado);
}
