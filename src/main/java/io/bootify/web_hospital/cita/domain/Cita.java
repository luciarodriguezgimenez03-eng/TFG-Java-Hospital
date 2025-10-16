package io.bootify.web_hospital.cita.domain;

import io.bootify.web_hospital.prescripcion.domain.Prescripcion;
import io.bootify.web_hospital.sala.domain.Sala;
import io.bootify.web_hospital.usuario.domain.Usuario;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Cita {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCita;

    @Column(nullable = false, columnDefinition = "longtext")
    private String descripcion;

    @Column(nullable = false)
    private LocalDate fechaCita;

    @Column(nullable = false)
    private LocalTime horaCita;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Usuario paciente;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empleado_id")
    private Usuario empleado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sala_id")
    private Sala sala;

    @OneToMany(mappedBy = "cita")
    private Set<Prescripcion> citaPrescripciones;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

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

    public Usuario getPaciente() {
        return paciente;
    }

    public void setPaciente(final Usuario paciente) {
        this.paciente = paciente;
    }

    public Usuario getEmpleado() {
        return empleado;
    }

    public void setEmpleado(final Usuario empleado) {
        this.empleado = empleado;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(final Sala sala) {
        this.sala = sala;
    }

    public Set<Prescripcion> getCitaPrescripciones() {
        return citaPrescripciones;
    }

    public void setCitaPrescripciones(final Set<Prescripcion> citaPrescripciones) {
        this.citaPrescripciones = citaPrescripciones;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(final OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(final OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
