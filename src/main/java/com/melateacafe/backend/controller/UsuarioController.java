package com.melateacafe.backend.controller;

import com.melateacafe.backend.dto.UsuarioDTO;
import com.melateacafe.backend.entity.Usuario;
import com.melateacafe.backend.service.UsuarioService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/usuario")
public class UsuarioController {
    private final Logger logger = Logger.getLogger(UsuarioController.class.getName());

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getAll() {
        logger.info("Obteniendo todos los usuarios");
        List<Usuario> usuarios = usuarioService.findAll();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        try {
            logger.info("Obteniendo usuario con id: " + id);
            Usuario usuario = usuarioService.findById(id);
            return ResponseEntity.ok(usuario);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<?> getByUsername(@PathVariable String username) {
        logger.info("Obteniendo usuario con username: " + username);
        return usuarioService.findByUsername(username)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Usuario>> getByEstado(@PathVariable boolean estado) {
        logger.info("Obteniendo usuarios con estado: " + estado);
        List<Usuario> usuarios = usuarioService.findByEstado(estado);
        return ResponseEntity.ok(usuarios);
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody UsuarioDTO usuarioDTO) {
        try {
            logger.info("Creando usuario: " + usuarioDTO.getUsername());
            Usuario usuario = usuarioService.save(usuarioDTO);

            usuario.setPassword(null);

            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(
            @PathVariable Integer id,
            @Valid @RequestBody UsuarioDTO usuarioDTO
    ) {
        try {
            logger.info("Actualizando usuario con id: " + id);
            Usuario usuario = usuarioService.update(id, usuarioDTO);

            usuario.setPassword(null);

            return ResponseEntity.ok(usuario);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            logger.info("Desactivando usuario con id: " + id);
            usuarioService.delete(id);
            return ResponseEntity.ok(Map.of("message", "Usuario desactivado exitosamente"));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/verificar-password")
    public ResponseEntity<?> verificarPassword(@RequestBody Map<String, String> credenciales) {
        try {
            String username = credenciales.get("username");
            String password = credenciales.get("password");

            Usuario usuario = usuarioService.findByUsername(username)
                    .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado"));

            boolean esValido = usuarioService.verificarPassword(password, usuario.getPassword());

            if (esValido) {
                return ResponseEntity.ok(Map.of(
                        "message", "Contraseña válida",
                        "usuarioId", usuario.getIdUsuario()
                ));
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Contraseña incorrecta"));
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
