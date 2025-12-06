package com.melateacafe.backend.dto.request.trabajador;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class UpdateTrabajadorRequestDTO {
    @NotNull(message = "El cargo es obligatorio")
    private Integer idCargo;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(min = 2, max = 255)
    private String nombres;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(min = 2, max = 255)
    private String apellidoPaterno;

    @Size(max = 255)
    private String apellidoMaterno;

    @NotBlank(message = "El email es obligatorio")
    @Email
    private String email;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{9}$")
    private String telefono;

    @NotBlank(message = "El número de documento es obligatorio")
    @Size(min = 8, max = 8)
    private String numeroDocumento;

    @NotNull(message = "La fecha de contratación es obligatoria")
    private LocalDate fechaContratacion;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

    public UpdateTrabajadorRequestDTO() {
    }

    public UpdateTrabajadorRequestDTO(Integer idCargo, String nombres, String apellidoPaterno, String apellidoMaterno, String email, String telefono, String numeroDocumento, LocalDate fechaContratacion, Boolean estado) {
        this.idCargo = idCargo;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.telefono = telefono;
        this.numeroDocumento = numeroDocumento;
        this.fechaContratacion = fechaContratacion;
        this.estado = estado;
    }

    public Integer getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Integer idCargo) {
        this.idCargo = idCargo;
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
}
