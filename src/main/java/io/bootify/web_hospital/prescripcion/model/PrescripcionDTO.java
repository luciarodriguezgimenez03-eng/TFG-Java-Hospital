package io.bootify.web_hospital.prescripcion.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;


public class PrescripcionDTO {

    private Integer idPrescripcion;

    @NotNull
    @Size(max = 100)
    private String dosis;

    @NotNull
    @Size(max = 100)
    private String frecuencia;

    @NotNull
    private LocalDate fechaInicio;

    @NotNull
    private LocalDate fechaFin;

    private String instrucciones;

    private Integer cita;

    private Integer medicamento;

    public Integer getIdPrescripcion() {
        return idPrescripcion;
    }

    public void setIdPrescripcion(final Integer idPrescripcion) {
        this.idPrescripcion = idPrescripcion;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(final String dosis) {
        this.dosis = dosis;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(final String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(final LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(final LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(final String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public Integer getCita() {
        return cita;
    }

    public void setCita(final Integer cita) {
        this.cita = cita;
    }

    public Integer getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(final Integer medicamento) {
        this.medicamento = medicamento;
    }

}
