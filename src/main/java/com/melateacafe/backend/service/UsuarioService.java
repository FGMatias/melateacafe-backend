package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.UsuarioDTO;
import com.melateacafe.backend.dto.request.usuario.CreateUsuarioRequestDTO;
import com.melateacafe.backend.dto.response.usuario.UsuarioResponseDTO;
import com.melateacafe.backend.entity.Usuario;

import java.util.List;
import java.util.Optional;
public interface UsuarioService {
    List<UsuarioResponseDTO> findAll();
    UsuarioResponseDTO findById(Integer id);
    UsuarioResponseDTO findByUsername(String username);
    UsuarioResponseDTO create(CreateUsuarioRequestDTO request);
    UsuarioResponseDTO update(Integer id, CreateUsuarioRequestDTO request);
    void delete(Integer id);
}

