package com.melateacafe.backend.dto.response.usuario;

import com.melateacafe.backend.dto.response.rol.RolResponseDTO;
import com.melateacafe.backend.dto.response.trabajador.TrabajadorResponseDTO;

import java.time.LocalDateTime;

public class UsuarioResponseDTO {
    private Integer idUsuario;
    private RolResponseDTO rol;
    private TrabajadorResponseDTO trabajador;
    private String username;
    private String email;
    private Boolean estado;
    private LocalDateTime fechaCreacion;

    public UsuarioResponseDTO() {
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public RolResponseDTO getRol() {
        return rol;
    }

    public void setRol(RolResponseDTO rol) {
        this.rol = rol;
    }

    public TrabajadorResponseDTO getTrabajador() {
        return trabajador;
    }

    public void setTrabajador(TrabajadorResponseDTO trabajador) {
        this.trabajador = trabajador;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
