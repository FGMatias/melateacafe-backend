package com.melateacafe.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "estado_mesa")
public class EstadoMesa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEstadoMesa;

    @Column(name = "nombre")
    private String nombre;

    public int getIdEstadoMesa() {
        return idEstadoMesa;
    }

    public void setIdEstadoMesa(int idEstadoMesa) {
        this.idEstadoMesa = idEstadoMesa;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
