package com.melateacafe.backend.dto.request.reserva;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class CreateReservaRequestDTO {
    @NotNull(message = "La mesa es obligatoria")
    private Integer idMesa;

    @NotNull(message = "El cliente es obligatorio")
    private Integer idCliente;

    @NotNull(message = "El número de personas es obligatorio")
    @Min(value = 1, message = "Debe haber al menos 1 persona")
    private Integer numeroPersonas;

    @Size(max = 500, message = "Las observaciones no pueden exceder 500 caracteres")
    private String observaciones;

    @NotNull(message = "La fecha de reserva es obligatoria")
    private LocalDate fechaReserva;

    private Boolean estado;

    public CreateReservaRequestDTO() {
    }

    public CreateReservaRequestDTO(Integer idMesa, Integer idCliente, Integer numeroPersonas, String observaciones, LocalDate fechaReserva, Boolean estado) {
        this.idMesa = idMesa;
        this.idCliente = idCliente;
        this.numeroPersonas = numeroPersonas;
        this.observaciones = observaciones;
        this.fechaReserva = fechaReserva;
        this.estado = estado;
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Integer getNumeroPersonas() {
        return numeroPersonas;
    }

    public void setNumeroPersonas(Integer numeroPersonas) {
        this.numeroPersonas = numeroPersonas;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}