package com.melateacafe.backend.dto.response.pedido;

public class EstadoPedidoResponseDTO {
    private Integer idEstadoPedido;
    private String nombre;

    public EstadoPedidoResponseDTO() {
    }

    public EstadoPedidoResponseDTO(Integer idEstadoPedido, String nombre) {
        this.idEstadoPedido = idEstadoPedido;
        this.nombre = nombre;
    }

    public Integer getIdEstadoPedido() {
        return idEstadoPedido;
    }

    public void setIdEstadoPedido(Integer idEstadoPedido) {
        this.idEstadoPedido = idEstadoPedido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
