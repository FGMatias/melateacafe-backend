package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.entity.Rol;
import com.melateacafe.backend.repository.RolRepository;
import com.melateacafe.backend.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {

    @Autowired
    private RolRepository rolRepository;

    @Override
    public List<Rol> findAll() {
        return rolRepository.findAll();
    }
}
