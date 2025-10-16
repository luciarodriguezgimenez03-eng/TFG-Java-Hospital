package io.bootify.web_hospital.especialidad.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class EspecialidadDTO {

    private Integer idEspecialidad;

    @NotNull
    @Size(max = 255)
    private String nombreEspecialidad;

    private String descripcion;

    public Integer getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(final Integer idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public String getNombreEspecialidad() {
        return nombreEspecialidad;
    }

    public void setNombreEspecialidad(final String nombreEspecialidad) {
        this.nombreEspecialidad = nombreEspecialidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

}
