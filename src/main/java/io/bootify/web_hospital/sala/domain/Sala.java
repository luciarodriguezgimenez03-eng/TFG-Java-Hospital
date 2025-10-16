package io.bootify.web_hospital.sala.domain;

import io.bootify.web_hospital.cita.domain.Cita;
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
public class Sala {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSala;

    @Column(nullable = false)
    private Boolean disponibilidad;

    @Column(nullable = false)
    private String nombreSala;

    @Column(nullable = false)
    private Integer numeroSala;

    @Column(nullable = false)
    private Integer plantaSala;

    @OneToMany(mappedBy = "sala")
    private Set<Cita> salaCitas;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

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

    public Set<Cita> getSalaCitas() {
        return salaCitas;
    }

    public void setSalaCitas(final Set<Cita> salaCitas) {
        this.salaCitas = salaCitas;
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
