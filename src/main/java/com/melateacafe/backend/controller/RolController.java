package com.melateacafe.backend.controller;

import com.melateacafe.backend.entity.Rol;
import com.melateacafe.backend.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/rol")
public class RolController {

    @Autowired
    private RolService rolService;

    @GetMapping
    public ResponseEntity<List<Rol>> findAll() {
        List<Rol> roles = rolService.findAll();
        return ResponseEntity.ok(roles);
    }
}
