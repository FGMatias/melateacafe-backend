package com.melateacafe.backend.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class ProductoDTO {

    @NotNull(message = "La categoría es obligatoria")
    private Integer idCategoria;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(
        min = 3,
        max = 255,
        message = "El nombre debe tener entre 3 a 255 caracteres"
    )
    private String nombre;

    @NotBlank(message = "La descripción es obligatoria")
    @Size(
        max = 500,
        message = "La descripción no puede exceder 500 caracteres"
    )
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(
        value = "0.01",
        message = "El precio debe ser mayor a 0"
    )
    @Digits(
        integer = 10,
        fraction = 2,
        message = "Formato de precio inválido"
    )
    private BigDecimal precio;

    private String imagenUrl;

    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stockActual;

    @Min(value = 0, message = "El stock mínimo no puede ser negativo")
    private Integer stockMinimo;

    private Boolean estado = true;

    public ProductoDTO() {
    }

    public ProductoDTO(Integer idCategoria, String nombre, String descripcion, BigDecimal precio, String imagenUrl, Integer stockActual, Integer stockMinimo, Boolean estado) {
        this.idCategoria = idCategoria;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagenUrl = imagenUrl;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.estado = estado;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    public Integer getStockActual() {
        return stockActual;
    }

    public void setStockActual(Integer stockActual) {
        this.stockActual = stockActual;
    }

    public Integer getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(Integer stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
