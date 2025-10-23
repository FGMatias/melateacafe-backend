package com.melateacafe.backend.service;

import com.melateacafe.backend.entity.Trabajador;

import java.util.List;
import java.util.Optional;

public interface TrabajadorService {
    Trabajador save(Trabajador trabajador);
    Trabajador update(Trabajador trabajador);
    void delete(Integer id);
    List<Trabajador> findAll();
    Trabajador findById(Integer id);
    List<Trabajador> findByCargo(Integer idCargo);
}
