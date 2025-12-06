package com.melateacafe.backend.dto.request.cliente;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateClienteRequestDTO {
    @NotNull(message = "El tipo de documento es obligatorio")
    private Integer idTipoDocumento;

    @NotBlank(message = "Los nombres son obligatorios")
    @Size(min = 2, max = 255, message = "Los nombres deben tener entre 2 y 255 caracteres")
    private String nombres;

    @NotBlank(message = "El apellido paterno es obligatorio")
    @Size(min = 2, max = 255, message = "El apellido paterno debe tener entre 2 y 255 caracteres")
    private String apellidoPaterno;

    @Size(max = 255, message = "El apellido materno no puede exceder 255 caracteres")
    private String apellidoMaterno;

    @NotBlank(message = "El número de documento es obligatorio")
    @Size(min = 8, max = 20, message = "El número de documento debe tener entre 8 y 20 caracteres")
    private String numeroDocumento;

    @Size(max = 255, message = "La razón social no puede exceder 255 caracteres")
    private String razonSocial;

    @NotBlank(message = "El teléfono es obligatorio")
    @Size(min = 9, max = 15, message = "El teléfono debe tener entre 9 y 15 caracteres")
    private String telefono;

    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El email debe ser válido")
    private String email;

    @Size(max = 255, message = "La dirección no puede exceder 255 caracteres")
    private String direccion;

    private Boolean estado = true;

    public CreateClienteRequestDTO() {
    }

    public CreateClienteRequestDTO(Integer idTipoDocumento, String nombres, String apellidoPaterno, String apellidoMaterno, String numeroDocumento, String razonSocial, String telefono, String email, String direccion, Boolean estado) {
        this.idTipoDocumento = idTipoDocumento;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.numeroDocumento = numeroDocumento;
        this.razonSocial = razonSocial;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.estado = estado;
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
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

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}