package com.melateacafe.backend.service;

import com.melateacafe.backend.entity.Cargo;

import java.util.List;

public interface CargoService {
    List<Cargo> findAll();
}
