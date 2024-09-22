package com.example.demo.entrypoint.dto;

public enum TipoEstadoEnum {

    SP("São Paulo"),
    RJ("Rio de Janeiro"),
    MG("Minas Gerais");

    private String estado;

    TipoEstadoEnum(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
