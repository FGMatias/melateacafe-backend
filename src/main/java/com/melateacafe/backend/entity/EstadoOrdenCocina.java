package com.melateacafe.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "estado_orden_cocina")
public class EstadoOrdenCocina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado_orden_cocina")
    private int idEstadoOrdenCocina;

    @Column(name = "nombre")
    private String nombre;

    public int getIdEstadoOrdenCocina() {
        return idEstadoOrdenCocina;
    }

    public void setIdEstadoOrdenCocina(int idEstadoOrdenCocina) {
        this.idEstadoOrdenCocina = idEstadoOrdenCocina;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
