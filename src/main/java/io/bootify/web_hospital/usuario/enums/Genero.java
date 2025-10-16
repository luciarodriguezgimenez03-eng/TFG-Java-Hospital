package io.bootify.web_hospital.usuario.enums;

public enum Genero {
    EL_MASCULINO("Él / Masculino"),
    ELLA_MASCULINO("Ella / Masculino"),
    ELLA_FEMENINO("Ella / Femenino"),
    EL_FEMENINO("Él / Femenino"),
    ELLE_FEMENINO("Elle / Femenino"),
    ELLE_MASCULINO("Elle / Masculino");

    private final String descripcion;

    Genero(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
