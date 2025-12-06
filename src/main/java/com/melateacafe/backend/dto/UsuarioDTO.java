package com.melateacafe.backend.dto;

import jakarta.validation.constraints.*;

public class UsuarioDTO {

    @NotNull(message = "El rol es obligatorio")
    private Integer idRol;

    @NotNull(message = "El trabajador es obligatorio")
    private Integer idTrabajador;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(
        min = 4,
        max = 50,
        message = "El nombre de usuario debe tener entre 4 y 50 caracteres"
    )
    @Pattern(
        regexp = "^[a-zA-Z0-9._-]+$",
        message = "El nombre de usuario solo puede contener letras, números, puntos, guiones y guiones bajos"
    )
    private String username;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, max = 100, message = "La contraseña debe tener entre 8 y 100 caracteres")
    @Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
        message = "La contraseña debe contener al menos: 1 número, 1 minúscula, 1 mayúscula y 1 carácter especial"
    )
    private String password;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;

    private Boolean estado = true;

    public UsuarioDTO() {
    }

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(Integer idRol) {
        this.idRol = idRol;
    }

    public Integer getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
