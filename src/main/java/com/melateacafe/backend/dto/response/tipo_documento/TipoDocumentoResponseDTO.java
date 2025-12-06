package com.melateacafe.backend.dto.response.tipo_documento;

public class TipoDocumentoResponseDTO {
    private Integer idTipoDocumento;
    private String nombre;

    public TipoDocumentoResponseDTO() {
    }

    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}