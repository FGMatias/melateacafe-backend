package com.melateacafe.backend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "detalle_compra_insumos")
public class DetalleCompraInsumos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_detalle_compra_insumos")
    private int idDetalleCompraInsumos;

    @ManyToOne
    @JoinColumn(name = "id_compra_insumo", referencedColumnName = "id_compra_insumo")
    private CompraInsumo compraInsumo;

    @ManyToOne
    @JoinColumn(name = "id_insumo", referencedColumnName = "id_insumo")
    private Insumo insumo;

    @Column(name = "cantidad")
    private BigDecimal cantidad;

    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;

    @Column(name = "subtotal")
    private BigDecimal subtotal;

    public int getIdDetalleCompraInsumos() {
        return idDetalleCompraInsumos;
    }

    public void setIdDetalleCompraInsumos(int idDetalleCompraInsumos) {
        this.idDetalleCompraInsumos = idDetalleCompraInsumos;
    }

    public CompraInsumo getCompraInsumo() {
        return compraInsumo;
    }

    public void setCompraInsumo(CompraInsumo compraInsumo) {
        this.compraInsumo = compraInsumo;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
}
