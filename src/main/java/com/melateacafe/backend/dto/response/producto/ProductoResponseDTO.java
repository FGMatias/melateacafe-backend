package com.melateacafe.backend.dto.response.producto;

import com.melateacafe.backend.dto.response.categoria.CategoriaResponseDTO;

import java.math.BigDecimal;

public class ProductoResponseDTO {
    private Integer idProducto;
    private CategoriaResponseDTO categoriaProducto;
    private String nombre;
    private String descripcion;
    private BigDecimal precio;
    private String imagenUrl;
    private Boolean estado;

    public ProductoResponseDTO() {
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public CategoriaResponseDTO getCategoriaProducto() {
        return categoriaProducto;
    }

    public void setCategoriaProducto(CategoriaResponseDTO categoriaProducto) {
        this.categoriaProducto = categoriaProducto;
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
