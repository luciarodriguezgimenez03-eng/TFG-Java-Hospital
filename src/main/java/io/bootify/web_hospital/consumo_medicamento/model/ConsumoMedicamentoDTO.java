package io.bootify.web_hospital.consumo_medicamento.model;

import jakarta.validation.constraints.NotNull;
import java.time.OffsetDateTime;
import org.springframework.format.annotation.DateTimeFormat;


public class ConsumoMedicamentoDTO {

    private Integer idConsumo;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime fechaHoraConsumo;

    @NotNull
    private Boolean confirmacion;

    private Integer prescripcion;

    private Integer paciente;

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

    public Integer getPrescripcion() {
        return prescripcion;
    }

    public void setPrescripcion(final Integer prescripcion) {
        this.prescripcion = prescripcion;
    }

    public Integer getPaciente() {
        return paciente;
    }

    public void setPaciente(final Integer paciente) {
        this.paciente = paciente;
    }

}
