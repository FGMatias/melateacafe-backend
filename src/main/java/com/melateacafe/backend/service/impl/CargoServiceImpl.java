package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.entity.Cargo;
import com.melateacafe.backend.repository.CargoRepository;
import com.melateacafe.backend.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoServiceImpl implements CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    @Override
    public List<Cargo> findAll() {
        return cargoRepository.findAll();
    }
}
