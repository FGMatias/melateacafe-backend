package com.melateacafe.backend.entity;

import com.melateacafe.backend.enums.TipoUnidadMedida;
import jakarta.persistence.*;

@Entity
@Table(name = "unidad_medida")
public class UnidadMedida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_unidad_medida")
    private int idUnidadMedida;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "abreviatura")
    private String abreviatura;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo", columnDefinition = "ENUM('peso', 'volumen', 'unidad', 'longitud')")
    private TipoUnidadMedida tipo;

    public int getIdUnidadMedida() {
        return idUnidadMedida;
    }

    public void setIdUnidadMedida(int idUnidadMedida) {
        this.idUnidadMedida = idUnidadMedida;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public TipoUnidadMedida getTipo() {
        return tipo;
    }

    public void setTipo(TipoUnidadMedida tipo) {
        this.tipo = tipo;
    }
}
