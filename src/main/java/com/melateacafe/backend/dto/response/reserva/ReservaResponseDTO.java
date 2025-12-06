package com.melateacafe.backend.dto.response.reserva;

import com.melateacafe.backend.dto.response.cliente.ClienteResponseDTO;
import com.melateacafe.backend.dto.response.mesa.MesaResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class ReservaResponseDTO {
    private Integer idReserva;
    private MesaResponseDTO mesa;
    private ClienteResponseDTO cliente;
    private Integer numeroPersonas;
    private String observaciones;
    private Boolean estado;
    private LocalDate fechaReserva;
    private LocalDateTime fechaCreacion;

    public ReservaResponseDTO() {
    }

    public Integer getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Integer idReserva) {
        this.idReserva = idReserva;
    }

    public MesaResponseDTO getMesa() {
        return mesa;
    }

    public void setMesa(MesaResponseDTO mesa) {
        this.mesa = mesa;
    }

    public ClienteResponseDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteResponseDTO cliente) {
        this.cliente = cliente;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public LocalDate getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDate fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}