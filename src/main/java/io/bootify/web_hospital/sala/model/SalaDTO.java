package io.bootify.web_hospital.sala.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class SalaDTO {

    private Integer idSala;

    @NotNull
    private Boolean disponibilidad;

    @NotNull
    @Size(max = 255)
    private String nombreSala;

    @NotNull
    private Integer numeroSala;

    @NotNull
    private Integer plantaSala;

    public Integer getIdSala() {
        return idSala;
    }

    public void setIdSala(final Integer idSala) {
        this.idSala = idSala;
    }

    public Boolean getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(final Boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public String getNombreSala() {
        return nombreSala;
    }

    public void setNombreSala(final String nombreSala) {
        this.nombreSala = nombreSala;
    }

    public Integer getNumeroSala() {
        return numeroSala;
    }

    public void setNumeroSala(final Integer numeroSala) {
        this.numeroSala = numeroSala;
    }

    public Integer getPlantaSala() {
        return plantaSala;
    }

    public void setPlantaSala(final Integer plantaSala) {
        this.plantaSala = plantaSala;
    }

}
