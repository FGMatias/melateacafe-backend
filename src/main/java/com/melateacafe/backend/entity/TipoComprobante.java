package com.melateacafe.backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tipo_comprobante")
public class TipoComprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTipoComprobante;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "correlativo")
    private int correlativo;

    @Column(name = "serie")
    private String serie;

    @Column(name = "formato_serie")
    private String formatoSerie;

    @Column(name = "codigo")
    private String codigo;

    public int getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(int idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(int correlativo) {
        this.correlativo = correlativo;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getFormatoSerie() {
        return formatoSerie;
    }

    public void setFormatoSerie(String formatoSerie) {
        this.formatoSerie = formatoSerie;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
