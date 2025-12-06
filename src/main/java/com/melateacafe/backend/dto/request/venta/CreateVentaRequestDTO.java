package com.melateacafe.backend.dto.request.venta;

import jakarta.validation.constraints.NotNull;

public class CreateVentaRequestDTO {
    @NotNull(message = "El método de pago es obligatorio")
    private Integer idMetodoPago;

    @NotNull(message = "El tipo de comprobante es obligatorio")
    private Integer idTipoComprobante;

    @NotNull(message = "El pedido es obligatorio")
    private Integer idPedido;

    @NotNull(message = "El usuario es obligatorio")
    private Integer idUsuario;

    public CreateVentaRequestDTO() {
    }

    public CreateVentaRequestDTO(Integer idMetodoPago, Integer idTipoComprobante, Integer idPedido, Integer idUsuario) {
        this.idMetodoPago = idMetodoPago;
        this.idTipoComprobante = idTipoComprobante;
        this.idPedido = idPedido;
        this.idUsuario = idUsuario;
    }

    public Integer getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(Integer idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public Integer getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(Integer idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}