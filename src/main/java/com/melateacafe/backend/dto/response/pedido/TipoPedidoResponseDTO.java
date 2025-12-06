package com.melateacafe.backend.dto.response.pedido;

public class TipoPedidoResponseDTO {
    private Integer idTipoPedido;
    private String nombre;

    public TipoPedidoResponseDTO() {
    }

    public TipoPedidoResponseDTO(Integer idTipoPedido, String nombre) {
        this.idTipoPedido = idTipoPedido;
        this.nombre = nombre;
    }

    public Integer getIdTipoPedido() {
        return idTipoPedido;
    }

    public void setIdTipoPedido(Integer idTipoPedido) {
        this.idTipoPedido = idTipoPedido;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
