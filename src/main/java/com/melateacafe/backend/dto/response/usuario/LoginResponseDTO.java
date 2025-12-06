package com.melateacafe.backend.dto.response.usuario;

import com.melateacafe.backend.entity.Usuario;

public class LoginResponseDTO {
    private String token;
    private UsuarioResponseDTO usuario;
    private String message;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token, UsuarioResponseDTO usuario, String message) {
        this.token = token;
        this.usuario = usuario;
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UsuarioResponseDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponseDTO usuario) {
        this.usuario = usuario;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
