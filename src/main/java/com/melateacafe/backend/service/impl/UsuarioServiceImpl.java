package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.dto.UsuarioDTO;
import com.melateacafe.backend.dto.request.usuario.CreateUsuarioRequestDTO;
import com.melateacafe.backend.dto.response.rol.RolResponseDTO;
import com.melateacafe.backend.dto.response.trabajador.TrabajadorResponseDTO;
import com.melateacafe.backend.dto.response.usuario.UsuarioResponseDTO;
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
import java.util.stream.Collectors;

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
    @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> findAll() {
        return usuarioRepository.findAll().stream()
                .map(this::convertToResponse)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO findById(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        return convertToResponse(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public UsuarioResponseDTO findByUsername(String username) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con username: " + username));
        return convertToResponse(usuario);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO create(CreateUsuarioRequestDTO request) {
        Trabajador trabajador = trabajadorRepository.findById(request.getIdTrabajador())
                .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado"));

        if (usuarioRepository.existsByTrabajador_IdTrabajador(request.getIdTrabajador())) {
            throw new IllegalStateException("Este trabajador ya tiene un usuario asociado");
        }

        if (usuarioRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("El username ya está en uso");
        }

        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("El email ya está en uso");
        }

        Rol rol = rolRepository.findById(request.getIdRol())
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));

        Usuario usuario = new Usuario();
        usuario.setTrabajador(trabajador);
        usuario.setRol(rol);
        usuario.setUsername(request.getUsername());
        usuario.setEmail(request.getEmail());
        usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        usuario.setEstado(true);
        usuario.setFechaCreacion(LocalDateTime.now());

        Usuario saved = usuarioRepository.save(usuario);
        return convertToResponse(saved);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO update(Integer id, CreateUsuarioRequestDTO request) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

        Trabajador trabajador = trabajadorRepository.findById(request.getIdTrabajador())
                .orElseThrow(() -> new EntityNotFoundException("Trabajador no encontrado"));

        Optional<Usuario> usuarioExistente = usuarioRepository.findByTrabajador_IdTrabajador(request.getIdTrabajador());
        if (usuarioExistente.isPresent() && usuarioExistente.get().getIdUsuario() != id) {
            throw new IllegalStateException("Este trabajador ya está asociado a otro usuario");
        }

        if (!usuario.getUsername().equals(request.getUsername())) {
            if (usuarioRepository.existsByUsername(request.getUsername())) {
                throw new IllegalArgumentException("El username ya está en uso");
            }
            usuario.setUsername(request.getUsername());
        }

        if (!usuario.getEmail().equals(request.getEmail())) {
            if (usuarioRepository.existsByEmail(request.getEmail())) {
                throw new IllegalArgumentException("El email ya está en uso");
            }
            usuario.setEmail(request.getEmail());
        }

        Rol rol = rolRepository.findById(request.getIdRol())
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado"));

        usuario.setTrabajador(trabajador);
        usuario.setRol(rol);

        if (request.getPassword() != null && !request.getPassword().isEmpty()) {
            usuario.setPassword(passwordEncoder.encode(request.getPassword()));
        }

        Usuario updated = usuarioRepository.save(usuario);
        return convertToResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));
        usuarioRepository.delete(usuario);
    }

    private UsuarioResponseDTO convertToResponse(Usuario usuario) {
        UsuarioResponseDTO response = new UsuarioResponseDTO();
        response.setIdUsuario(usuario.getIdUsuario());
        response.setRol(convertRolToResponse(usuario.getRol()));

        if (usuario.getTrabajador() != null) {
            response.setTrabajador(convertTrabajadorToResponse(usuario.getTrabajador()));
        }

        response.setUsername(usuario.getUsername());
        response.setEmail(usuario.getEmail());
        response.setEstado(usuario.isEstado());
        response.setFechaCreacion(usuario.getFechaCreacion());

        return response;
    }

    private RolResponseDTO convertRolToResponse(Rol rol) {
        RolResponseDTO response = new RolResponseDTO();
        response.setIdRol(rol.getIdRol());
        response.setNombre(rol.getNombre());
        response.setEstado(rol.isEstado());
        response.setFechaCreacion(rol.getFechaCreacion());
        return response;
    }

    private TrabajadorResponseDTO convertTrabajadorToResponse(Trabajador trabajador) {
        TrabajadorResponseDTO response = new TrabajadorResponseDTO();
        response.setIdTrabajador(trabajador.getIdTrabajador());
        response.setNombres(trabajador.getNombres());
        response.setApellidoPaterno(trabajador.getApellidoPaterno());
        response.setApellidoMaterno(trabajador.getApellidoMaterno());
        return response;
    }
}
