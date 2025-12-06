package com.melateacafe.backend.dto.request.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UpdateCategoriaRequestDTO {
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 255, message = "El nombre debe tener entre 2 y 255 caracteres")
    private String nombre;

    @Size(max = 500, message = "La descripción no puede exceder 500 caracteres")
    private String descripcion;

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
