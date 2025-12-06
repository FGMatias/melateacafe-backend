package com.melateacafe.backend.dto.response.mesa;

public class EstadoMesaResponseDTO {
    private Integer idEstadoMesa;
    private String nombre;

    public EstadoMesaResponseDTO() {
    }

    public Integer getIdEstadoMesa() {
        return idEstadoMesa;
    }

    public void setIdEstadoMesa(Integer idEstadoMesa) {
        this.idEstadoMesa = idEstadoMesa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}