package io.bootify.web_hospital.role.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;


public class RoleDTO {

    private Integer idRol;

    @NotNull
    @Size(max = 50)
    private String nombreRol;

    public Integer getIdRol() {
        return idRol;
    }

    public void setIdRol(final Integer idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(final String nombreRol) {
        this.nombreRol = nombreRol;
    }

}
