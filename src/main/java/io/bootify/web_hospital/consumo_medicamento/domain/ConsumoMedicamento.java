package io.bootify.web_hospital.consumo_medicamento.domain;

import io.bootify.web_hospital.prescripcion.domain.Prescripcion;
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
import java.time.OffsetDateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class ConsumoMedicamento {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idConsumo;

    @Column(nullable = false)
    private OffsetDateTime fechaHoraConsumo;

    @Column(nullable = false)
    private Boolean confirmacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescripcion_id")
    private Prescripcion prescripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "paciente_id")
    private Usuario paciente;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

    public Integer getIdConsumo() {
        return idConsumo;
    }

    public void setIdConsumo(final Integer idConsumo) {
        this.idConsumo = idConsumo;
    }

    public OffsetDateTime getFechaHoraConsumo() {
        return fechaHoraConsumo;
    }

    public void setFechaHoraConsumo(final OffsetDateTime fechaHoraConsumo) {
        this.fechaHoraConsumo = fechaHoraConsumo;
    }

    public Boolean getConfirmacion() {
        return confirmacion;
    }

    public void setConfirmacion(final Boolean confirmacion) {
        this.confirmacion = confirmacion;
    }

    public Prescripcion getPrescripcion() {
        return prescripcion;
    }

    public void setPrescripcion(final Prescripcion prescripcion) {
        this.prescripcion = prescripcion;
    }

    public Usuario getPaciente() {
        return paciente;
    }

    public void setPaciente(final Usuario paciente) {
        this.paciente = paciente;
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
