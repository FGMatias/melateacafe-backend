package com.melateacafe.backend.dto.response.trabajador;

import com.melateacafe.backend.dto.response.cargo.CargoResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class TrabajadorResponseDTO {
    private Integer idTrabajador;
    private CargoResponseDTO cargo;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String email;
    private String telefono;
    private String numeroDocumento;
    private LocalDate fechaContratacion;
    private Boolean estado;
    private LocalDateTime fechaCreacion;
    private Boolean tieneUsuario;

    public TrabajadorResponseDTO() {
    }

    public Integer getIdTrabajador() {
        return idTrabajador;
    }

    public void setIdTrabajador(Integer idTrabajador) {
        this.idTrabajador = idTrabajador;
    }

    public CargoResponseDTO getCargo() {
        return cargo;
    }

    public void setCargo(CargoResponseDTO cargo) {
        this.cargo = cargo;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public LocalDate getFechaContratacion() {
        return fechaContratacion;
    }

    public void setFechaContratacion(LocalDate fechaContratacion) {
        this.fechaContratacion = fechaContratacion;
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

    public Boolean getTieneUsuario() {
        return tieneUsuario;
    }

    public void setTieneUsuario(Boolean tieneUsuario) {
        this.tieneUsuario = tieneUsuario;
    }
}
