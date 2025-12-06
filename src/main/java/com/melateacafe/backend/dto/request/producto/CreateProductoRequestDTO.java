package com.melateacafe.backend.dto.request.producto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public class CreateProductoRequestDTO {
    @NotNull(message = "La categoría es obligatoria")
    private Integer idCategoriaProducto;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 255, message = "El nombre debe tener entre 2 y 255 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a 0")
    @Digits(integer = 8, fraction = 2, message = "El precio debe tener máximo 8 dígitos enteros y 2 decimales")
    private BigDecimal precio;

    @Size(max = 500, message = "La URL de la imagen no puede exceder 500 caracteres")
    private String imagenUrl;

    private Boolean estado;

    public CreateProductoRequestDTO() {
    }

    public CreateProductoRequestDTO(Integer idCategoriaProducto, String nombre, String descripcion, BigDecimal precio, String imagenUrl, Boolean estado) {
        this.idCategoriaProducto = idCategoriaProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagenUrl = imagenUrl;
        this.estado = estado;
    }

    public Integer getIdCategoriaProducto() {
        return idCategoriaProducto;
    }

    public void setIdCategoriaProducto(Integer idCategoriaProducto) {
        this.idCategoriaProducto = idCategoriaProducto;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
