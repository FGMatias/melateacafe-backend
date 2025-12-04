package com.melateacafe.backend.dto.response;

import com.melateacafe.backend.entity.Usuario;

public class LoginResponseDTO {
    private String token;
    private Usuario usuario;
    private String message;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String token, Usuario usuario, String message) {
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
