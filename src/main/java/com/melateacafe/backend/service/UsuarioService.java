package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.UsuarioDTO;
import com.melateacafe.backend.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario save(UsuarioDTO usuarioDTO);
    Usuario update(Integer id, UsuarioDTO usuarioDTO);
    void delete(Integer id);
    List<Usuario> findAll();
    Usuario findById(Integer id);
    Optional<Usuario> findByUsername(String username);
    List<Usuario> findByEstado(boolean estado);
    boolean verificarPassword(String password, String passwordEncriptado);
}
