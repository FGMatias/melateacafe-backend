package com.melateacafe.backend.service.impl;

import com.melateacafe.backend.dto.request.LoginRequestDTO;
import com.melateacafe.backend.dto.response.LoginResponseDTO;
import com.melateacafe.backend.entity.Usuario;
import com.melateacafe.backend.security.JwtService;
import com.melateacafe.backend.service.AuthService;
import com.melateacafe.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Service
public class AuthServiceImpl implements AuthService {
    private final Logger logger = Logger.getLogger(AuthServiceImpl.class.getName());

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    @Transactional(readOnly = true)
    public LoginResponseDTO login(LoginRequestDTO loginRequest) {
        try {
            logger.info("Iniciando proceso de autenticación para usuario: " + loginRequest.getUsername());

            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            logger.info("Autenticación exitosa para: " + loginRequest.getUsername());

            UserDetails userDetails = userDetailsService.loadUserByUsername(loginRequest.getUsername());

            String token = jwtService.generateToken(userDetails);
            logger.info("Token JWT generado para: " + loginRequest.getUsername());

            Usuario usuario = usuarioService.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado después de autenticación"));

            usuario.setPassword(null);

            LoginResponseDTO response = new LoginResponseDTO(token, usuario, "Login exitoso");

            logger.info("Login completado exitosamente para: " + loginRequest.getUsername());

            return response;

        } catch (BadCredentialsException e) {
            logger.warning("Credenciales incorrectas para usuario: " + loginRequest.getUsername());
            throw new BadCredentialsException("Usuario o contraseña incorrectos");

        } catch (DisabledException e) {
            logger.warning("Intento de login con usuario inactivo: " + loginRequest.getUsername());
            throw new DisabledException("La cuenta de usuario está desactivada");

        } catch (Exception e) {
            logger.severe("Error inesperado en login: " + e.getMessage());
            throw new RuntimeException("Error durante el proceso de autenticación: " + e.getMessage());
        }
    }

    @Override
    public boolean validateToken(String token) {
        try {
            String username = jwtService.extractUsername(token);
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return jwtService.validateToken(token, userDetails);
        } catch (Exception e) {
            logger.warning("Token inválido: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String extractUsernameFromToken(String token) {
        try {
            return jwtService.extractUsername(token);
        } catch (Exception e) {
            logger.warning("Error al extraer username del token: " + e.getMessage());
            throw new RuntimeException("Token inválido");
        }
    }
}
