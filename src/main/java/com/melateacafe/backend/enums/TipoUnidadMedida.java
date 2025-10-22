package com.melateacafe.backend.enums;

public enum TipoUnidadMedida {
    PESO("peso"),
    VOLUMEN("volumen"),
    UNIDAD("unidad"),
    LONGITUD("longitud");

    private final String valor;

    TipoUnidadMedida(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

    public static TipoUnidadMedida fromValor(String valor) {
        for (TipoUnidadMedida tipo : TipoUnidadMedida.values()) {
            if (tipo.valor.equalsIgnoreCase(valor)) {
                return tipo;
            }
        }

        throw new IllegalArgumentException("Tipo de unidad de medida no válido: " + valor);
    }

    @Override
    public String toString() {
        return this.valor;
    }
}
