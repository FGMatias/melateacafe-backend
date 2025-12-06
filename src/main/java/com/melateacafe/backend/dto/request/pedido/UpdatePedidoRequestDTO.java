package com.melateacafe.backend.dto.request.pedido;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdatePedidoRequestDTO {
    @NotNull(message = "El ID del estado es obligatorio")
    private Integer idEstado;

    @Size(max = 255, message = "Las observaciones no pueden exceder 255 caracteres")
    private String observaciones;

    public UpdatePedidoRequestDTO() {}

    public Integer getIdEstado() { return idEstado; }
    public void setIdEstado(Integer idEstado) { this.idEstado = idEstado; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}