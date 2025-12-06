package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.request.usuario.CreateUsuarioRequestDTO;
import com.melateacafe.backend.dto.request.usuario.UpdateUsuarioRequestDTO;
import com.melateacafe.backend.dto.response.usuario.UsuarioResponseDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioResponseDTO> findAll();
    UsuarioResponseDTO findById(Integer id);
    UsuarioResponseDTO findByUsername(String username);
    UsuarioResponseDTO create(CreateUsuarioRequestDTO request);
    UsuarioResponseDTO update(Integer id, UpdateUsuarioRequestDTO request);
    void delete(Integer id);
}

