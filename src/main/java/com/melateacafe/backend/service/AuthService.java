package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.request.usuario.LoginRequestDTO;
import com.melateacafe.backend.dto.response.usuario.LoginResponseDTO;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO dto);
    boolean validateToken(String token);
    String extractUsernameFromToken(String token);
}
