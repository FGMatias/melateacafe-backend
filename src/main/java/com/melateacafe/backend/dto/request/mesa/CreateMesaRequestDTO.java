package com.melateacafe.backend.dto.request.mesa;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateMesaRequestDTO {
    @NotNull(message = "El estado de la mesa es obligatorio")
    private Integer idEstado;

    @NotBlank(message = "El número de mesa es obligatorio")
    @Size(min = 1, max = 10, message = "El número debe tener entre 1 y 10 caracteres")
    private String numero;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad debe ser al menos 1 persona")
    private Integer capacidad;

    private Boolean estado;

    public CreateMesaRequestDTO() {
    }

    public CreateMesaRequestDTO(Integer idEstado, String numero, Integer capacidad, Boolean estado) {
        this.idEstado = idEstado;
        this.numero = numero;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}