package com.melateacafe.backend.dto.response.mesa;

public class MesaResponseDTO {
    private Integer idMesa;
    private EstadoMesaResponseDTO estadoMesa;
    private String numero;
    private Integer capacidad;
    private Boolean estado;

    public MesaResponseDTO() {
    }

    public Integer getIdMesa() {
        return idMesa;
    }

    public void setIdMesa(Integer idMesa) {
        this.idMesa = idMesa;
    }

    public EstadoMesaResponseDTO getEstadoMesa() {
        return estadoMesa;
    }

    public void setEstadoMesa(EstadoMesaResponseDTO estadoMesa) {
        this.estadoMesa = estadoMesa;
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