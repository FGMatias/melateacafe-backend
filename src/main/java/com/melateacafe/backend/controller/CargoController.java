package com.melateacafe.backend.controller;

import com.melateacafe.backend.entity.Cargo;
import com.melateacafe.backend.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/cargo")
public class CargoController {

    @Autowired
    private CargoService cargoService;

    @GetMapping
    public ResponseEntity<List<Cargo>> findAll() {
        List<Cargo> cargos = cargoService.findAll();
        return ResponseEntity.ok(cargos);
    }
}
