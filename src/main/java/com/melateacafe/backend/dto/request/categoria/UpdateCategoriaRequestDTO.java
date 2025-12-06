package com.melateacafe.backend.dto.request.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateCategoriaRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 255)
    private String nombre;

    @Size(max = 500)
    private String descripcion;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

    public UpdateCategoriaRequestDTO() {
    }

    public UpdateCategoriaRequestDTO(String nombre, String descripcion, Boolean estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.estado = estado;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
}
