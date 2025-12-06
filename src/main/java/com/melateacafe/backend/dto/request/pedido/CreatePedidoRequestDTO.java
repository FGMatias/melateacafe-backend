package com.melateacafe.backend.dto.request.pedido;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.util.List;

public class CreatePedidoRequestDTO {
    private Integer idMesa;

    @NotNull(message = "El cliente es obligatorio")
    private Integer idCliente;

    @NotNull(message = "El estado del pedido es obligatorio")
    private Integer idEstado;

    @NotNull(message = "El tipo de pedido es obligatorio")
    private Integer idTipoPedido;

    @Size(max = 255, message = "La dirección de entrega no puede exceder 255 caracteres")
    private String direccionEntrega;

    @Size(max = 500, message = "Las observaciones no pueden exceder 500 caracteres")
    private String observaciones;

    private BigDecimal deliveryCosto;

    @NotEmpty(message = "El pedido debe tener al menos un producto")
    @Valid
    private List<DetallePedidoRequestDTO> detalles;

    public CreatePedidoRequestDTO() {
    }

    public CreatePedidoRequestDTO(Integer idMesa, Integer idCliente, Integer idEstado, Integer idTipoPedido, String direccionEntrega, String observaciones, BigDecimal deliveryCosto, List<DetallePedidoRequestDTO> detalles) {
        this.idMesa = idMesa;
        this.idCliente = idCliente;
        this.idEstado = idEstado;
        this.idTipoPedido = idTipoPedido;
        this.direccionEntrega = direccionEntrega;
        this.observaciones = observaciones;
        this.deliveryCosto = deliveryCosto;
        this.detalles = detalles;
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

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public Integer getIdTipoPedido() {
        return idTipoPedido;
    }

    public void setIdTipoPedido(Integer idTipoPedido) {
        this.idTipoPedido = idTipoPedido;
    }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public BigDecimal getDeliveryCosto() {
        return deliveryCosto;
    }

    public void setDeliveryCosto(BigDecimal deliveryCosto) {
        this.deliveryCosto = deliveryCosto;
    }

    public List<DetallePedidoRequestDTO> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetallePedidoRequestDTO> detalles) {
        this.detalles = detalles;
    }
}