package com.melateacafe.backend.dto.response.venta;

public class TipoComprobanteResponseDTO {
    private Integer idTipoComprobante;
    private String nombre;
    private Integer correlativo;
    private String serie;
    private String formatoSerie;
    private String codigo;

    public TipoComprobanteResponseDTO() {
    }

    public Integer getIdTipoComprobante() {
        return idTipoComprobante;
    }

    public void setIdTipoComprobante(Integer idTipoComprobante) {
        this.idTipoComprobante = idTipoComprobante;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCorrelativo() {
        return correlativo;
    }

    public void setCorrelativo(Integer correlativo) {
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