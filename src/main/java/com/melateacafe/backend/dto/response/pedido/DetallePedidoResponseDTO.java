package com.melateacafe.backend.dto.response.pedido;

import com.melateacafe.backend.dto.response.producto.ProductoResponseDTO;
import java.math.BigDecimal;

public class DetallePedidoResponseDTO {
    private Integer idDetallePedido;
    private ProductoResponseDTO producto;
    private Integer cantidad;
    private BigDecimal precioUnitario;
    private BigDecimal subtotal;
    private String observaciones;

    public DetallePedidoResponseDTO() {}

    public Integer getIdDetallePedido() { return idDetallePedido; }
    public void setIdDetallePedido(Integer idDetallePedido) { this.idDetallePedido = idDetallePedido; }

    public ProductoResponseDTO getProducto() { return producto; }
    public void setProducto(ProductoResponseDTO producto) { this.producto = producto; }

    public Integer getCantidad() { return cantidad; }
    public void setCantidad(Integer cantidad) { this.cantidad = cantidad; }

    public BigDecimal getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(BigDecimal precioUnitario) { this.precioUnitario = precioUnitario; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}