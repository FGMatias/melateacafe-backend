package com.melateacafe.backend.dto.request.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateUsuarioRequestDTO {
    @NotNull(message = "El trabajador es obligatorio")
    private Integer idTrabajador;

    @NotNull(message = "El rol es obligatorio")
    private Integer idRol;

    @NotBlank(message = "El username es obligatorio")
    @Size(min = 4, max = 50)
    private String username;

    @NotBlank(message = "El email es obligatorio")
    @Email
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

    public CreateUsuarioRequestDTO() {
    }

    public CreateUsuarioRequestDTO(Integer idTrabajador, Integer idRol, String username, String email, String password) {
        this.idTrabajador = idTrabajador;
        this.idRol = idRol;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Integer getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
