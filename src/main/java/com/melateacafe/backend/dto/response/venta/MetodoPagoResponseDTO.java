package com.melateacafe.backend.dto.response.venta;

public class MetodoPagoResponseDTO {
    private Integer idMetodoPago;
    private String nombre;

    public MetodoPagoResponseDTO() {
    }

    public Integer getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(Integer idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}