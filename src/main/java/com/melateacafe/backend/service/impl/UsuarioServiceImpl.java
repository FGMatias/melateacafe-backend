package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.dto.UsuarioDTO;
import com.melateacafe.backend.entity.Rol;
import com.melateacafe.backend.entity.Trabajador;
import com.melateacafe.backend.entity.Usuario;
import com.melateacafe.backend.repository.RolRepository;
import com.melateacafe.backend.repository.TrabajadorRepository;
import com.melateacafe.backend.repository.UsuarioRepository;
import com.melateacafe.backend.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private TrabajadorRepository trabajadorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public Usuario save(UsuarioDTO usuarioDTO) {
        if (usuarioRepository.existsByUsername(usuarioDTO.getUsername())) {
            throw new IllegalArgumentException("El nombre de usuario ya existe");
        }

        if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        Rol rol = rolRepository.findById(usuarioDTO.getIdRol())
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));

        Trabajador trabajador = null;
        if (usuarioDTO.getIdTrabajador() != null) {
            trabajador = trabajadorRepository.findById(usuarioDTO.getIdTrabajador())
                    .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado"));
        }

        Usuario usuario = new Usuario();
        usuario.setRol(rol);
        usuario.setTrabajador(trabajador);
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellidoPaterno(usuarioDTO.getApellidoPaterno());
        usuario.setApellidoMaterno(usuarioDTO.getApellidoMaterno());
        usuario.setUsername(usuarioDTO.getUsername());

        String passwordEncriptado = passwordEncoder.encode(usuarioDTO.getPassword());
        usuario.setPassword(passwordEncriptado);

        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setEstado(usuarioDTO.getEstado() != null ? usuarioDTO.getEstado() : true);
        usuario.setFechaCreacion(LocalDateTime.now());

        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Usuario update(Integer id, UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        if (!usuario.getUsername().equals(usuarioDTO.getUsername())) {
            if (usuarioRepository.existsByUsername((usuarioDTO.getUsername()))) {
                throw new IllegalArgumentException("El nombre de usuario ya existe");
            }

            usuario.setUsername(usuarioDTO.getUsername());
        }

        if (!usuario.getEmail().equals(usuarioDTO.getEmail())) {
            if (usuarioRepository.existsByEmail(usuarioDTO.getEmail())) {
                throw new IllegalArgumentException("El email ya esta registrado");
            }

            usuario.setEmail(usuario.getEmail());
        }

        Rol rol = rolRepository.findById(usuarioDTO.getIdRol())
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));

        usuario.setRol(rol);

        if (usuarioDTO.getIdTrabajador() != null) {
            Trabajador trabajador = trabajadorRepository.findById(usuarioDTO.getIdTrabajador())
                    .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado"));

            usuario.setTrabajador(trabajador);
        }

        usuario.setNombre(usuario.getNombre());
        usuario.setApellidoPaterno(usuarioDTO.getApellidoPaterno());
        usuario.setApellidoMaterno(usuarioDTO.getApellidoMaterno());

        if (usuarioDTO.getPassword() != null && !usuarioDTO.getPassword().isEmpty()) {
            String passwordEncriptado = passwordEncoder.encode(usuarioDTO.getPassword());
            usuario.setPassword(passwordEncriptado);
        }

        usuario.setEstado(usuarioDTO.getEstado());

        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        usuario.setEstado(false);
        usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    @Override
    public List<Usuario> findByEstado(boolean estado) {
        return usuarioRepository.findByEstado(estado);
    }

    @Override
    public boolean verificarPassword(String password, String passwordEncriptado) {
        return passwordEncoder.matches(password, passwordEncriptado);
    }
}
