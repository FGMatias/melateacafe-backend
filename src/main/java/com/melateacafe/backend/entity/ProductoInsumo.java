package com.melateacafe.backend.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "producto_insumo")
public class ProductoInsumo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProductoInsumo;

    @ManyToOne
    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_insumo", referencedColumnName = "id_insumo")
    private Insumo insumo;

    @Column(name = "cantidad_necesaria")
    private BigDecimal cantidadNecesaria;

    public int getIdProductoInsumo() {
        return idProductoInsumo;
    }

    public void setIdProductoInsumo(int idProductoInsumo) {
        this.idProductoInsumo = idProductoInsumo;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Insumo getInsumo() {
        return insumo;
    }

    public void setInsumo(Insumo insumo) {
        this.insumo = insumo;
    }

    public BigDecimal getCantidadNecesaria() {
        return cantidadNecesaria;
    }

    public void setCantidadNecesaria(BigDecimal cantidadNecesaria) {
        this.cantidadNecesaria = cantidadNecesaria;
    }
}
