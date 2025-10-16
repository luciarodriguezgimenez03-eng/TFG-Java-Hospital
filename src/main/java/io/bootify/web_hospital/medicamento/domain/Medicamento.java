package io.bootify.web_hospital.medicamento.domain;

import io.bootify.web_hospital.prescripcion.domain.Prescripcion;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.time.OffsetDateTime;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Medicamento {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMedicamento;

    @Column(nullable = false)
    private String nombre;

    @Column(columnDefinition = "longtext")
    private String descripcion;

    @Column(nullable = false, length = 100)
    private String presentacion;

    @OneToMany(mappedBy = "medicamento")
    private Set<Prescripcion> medicamentoPrescripciones;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Integer getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(final Integer idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(final String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(final String presentacion) {
        this.presentacion = presentacion;
    }

    public Set<Prescripcion> getMedicamentoPrescripciones() {
        return medicamentoPrescripciones;
    }

    public void setMedicamentoPrescripciones(final Set<Prescripcion> medicamentoPrescripciones) {
        this.medicamentoPrescripciones = medicamentoPrescripciones;
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
