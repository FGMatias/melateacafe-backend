package com.melateacafe.backend.service;

import com.melateacafe.backend.dto.request.LoginRequestDTO;
import com.melateacafe.backend.dto.response.LoginResponseDTO;

public interface AuthService {
    LoginResponseDTO login(LoginRequestDTO dto);
    boolean validateToken(String token);
    String extractUsernameFromToken(String token);
}
