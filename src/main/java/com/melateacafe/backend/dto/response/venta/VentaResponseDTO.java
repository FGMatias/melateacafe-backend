package com.melateacafe.backend.dto.response.venta;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class VentaResponseDTO {
    private Integer idVenta;
    private MetodoPagoResponseDTO metodoPago;
    private TipoComprobanteResponseDTO tipoComprobante;
    private Integer idPedido;
    private Integer idUsuario;
    private String numeroComprobante;
    private BigDecimal subtotal;
    private BigDecimal igv;
    private BigDecimal total;
    private LocalDate fecha;
    private LocalTime hora;

    public VentaResponseDTO() {}

    public Integer getIdVenta() { return idVenta; }
    public void setIdVenta(Integer idVenta) { this.idVenta = idVenta; }

    public MetodoPagoResponseDTO getMetodoPago() { return metodoPago; }
    public void setMetodoPago(MetodoPagoResponseDTO metodoPago) { this.metodoPago = metodoPago; }

    public TipoComprobanteResponseDTO getTipoComprobante() { return tipoComprobante; }
    public void setTipoComprobante(TipoComprobanteResponseDTO tipoComprobante) { this.tipoComprobante = tipoComprobante; }

    public Integer getIdPedido() { return idPedido; }
    public void setIdPedido(Integer idPedido) { this.idPedido = idPedido; }

    public Integer getIdUsuario() { return idUsuario; }
    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public String getNumeroComprobante() { return numeroComprobante; }
    public void setNumeroComprobante(String numeroComprobante) { this.numeroComprobante = numeroComprobante; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    public BigDecimal getIgv() { return igv; }
    public void setIgv(BigDecimal igv) { this.igv = igv; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHora() { return hora; }
    public void setHora(LocalTime hora) { this.hora = hora; }
}