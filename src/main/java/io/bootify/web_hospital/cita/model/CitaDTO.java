package io.bootify.web_hospital.cita.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;


public class CitaDTO {

    private Integer idCita;

    @NotNull
    private String descripcion;

    @NotNull
    private LocalDate fechaCita;

    @NotNull
    @Schema(type = "string", example = "18:30")
    private LocalTime horaCita;

    private Integer paciente;

    private Integer empleado;

    private Integer sala;

    public Integer getIdCita() {
        return idCita;
    }

    public void setIdCita(final Integer idCita) {
        this.idCita = idCita;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    public LocalDate getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(final LocalDate fechaCita) {
        this.fechaCita = fechaCita;
    }

    public LocalTime getHoraCita() {
        return horaCita;
    }

    public void setHoraCita(final LocalTime horaCita) {
        this.horaCita = horaCita;
    }

    public Integer getPaciente() {
        return paciente;
    }

    public void setPaciente(final Integer paciente) {
        this.paciente = paciente;
    }

    public Integer getEmpleado() {
        return empleado;
    }

    public void setEmpleado(final Integer empleado) {
        this.empleado = empleado;
    }

    public Integer getSala() {
        return sala;
    }

    public void setSala(final Integer sala) {
        this.sala = sala;
    }

}
