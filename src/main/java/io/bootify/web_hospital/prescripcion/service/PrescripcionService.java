package io.bootify.web_hospital.prescripcion.service;

import io.bootify.web_hospital.cita.domain.Cita;
import io.bootify.web_hospital.cita.repos.CitaRepository;
import io.bootify.web_hospital.consumo_medicamento.domain.ConsumoMedicamento;
import io.bootify.web_hospital.consumo_medicamento.repos.ConsumoMedicamentoRepository;
import io.bootify.web_hospital.medicamento.domain.Medicamento;
import io.bootify.web_hospital.medicamento.repos.MedicamentoRepository;
import io.bootify.web_hospital.prescripcion.domain.Prescripcion;
import io.bootify.web_hospital.prescripcion.model.PrescripcionDTO;
import io.bootify.web_hospital.prescripcion.repos.PrescripcionRepository;
import io.bootify.web_hospital.util.NotFoundException;
import io.bootify.web_hospital.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class PrescripcionService {

    private final PrescripcionRepository prescripcionRepository;
    private final CitaRepository citaRepository;
    private final MedicamentoRepository medicamentoRepository;
    private final ConsumoMedicamentoRepository consumoMedicamentoRepository;

    public PrescripcionService(final PrescripcionRepository prescripcionRepository,
            final CitaRepository citaRepository, final MedicamentoRepository medicamentoRepository,
            final ConsumoMedicamentoRepository consumoMedicamentoRepository) {
        this.prescripcionRepository = prescripcionRepository;
        this.citaRepository = citaRepository;
        this.medicamentoRepository = medicamentoRepository;
        this.consumoMedicamentoRepository = consumoMedicamentoRepository;
    }

    public List<PrescripcionDTO> findAll() {
        final List<Prescripcion> prescripcions = prescripcionRepository.findAll(Sort.by("idPrescripcion"));
        return prescripcions.stream()
                .map(prescripcion -> mapToDTO(prescripcion, new PrescripcionDTO()))
                .toList();
    }

    public PrescripcionDTO get(final Integer idPrescripcion) {
        return prescripcionRepository.findById(idPrescripcion)
                .map(prescripcion -> mapToDTO(prescripcion, new PrescripcionDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final PrescripcionDTO prescripcionDTO) {
        final Prescripcion prescripcion = new Prescripcion();
        mapToEntity(prescripcionDTO, prescripcion);
        return prescripcionRepository.save(prescripcion).getIdPrescripcion();
    }

    public void update(final Integer idPrescripcion, final PrescripcionDTO prescripcionDTO) {
        final Prescripcion prescripcion = prescripcionRepository.findById(idPrescripcion)
                .orElseThrow(NotFoundException::new);
        mapToEntity(prescripcionDTO, prescripcion);
        prescripcionRepository.save(prescripcion);
    }

    public void delete(final Integer idPrescripcion) {
        prescripcionRepository.deleteById(idPrescripcion);
    }

    private PrescripcionDTO mapToDTO(final Prescripcion prescripcion,
            final PrescripcionDTO prescripcionDTO) {
        prescripcionDTO.setIdPrescripcion(prescripcion.getIdPrescripcion());
        prescripcionDTO.setDosis(prescripcion.getDosis());
        prescripcionDTO.setFrecuencia(prescripcion.getFrecuencia());
        prescripcionDTO.setFechaInicio(prescripcion.getFechaInicio());
        prescripcionDTO.setFechaFin(prescripcion.getFechaFin());
        prescripcionDTO.setInstrucciones(prescripcion.getInstrucciones());
        prescripcionDTO.setCita(prescripcion.getCita() == null ? null : prescripcion.getCita().getIdCita());
        prescripcionDTO.setMedicamento(prescripcion.getMedicamento() == null ? null : prescripcion.getMedicamento().getIdMedicamento());
        return prescripcionDTO;
    }

    private Prescripcion mapToEntity(final PrescripcionDTO prescripcionDTO,
            final Prescripcion prescripcion) {
        prescripcion.setDosis(prescripcionDTO.getDosis());
        prescripcion.setFrecuencia(prescripcionDTO.getFrecuencia());
        prescripcion.setFechaInicio(prescripcionDTO.getFechaInicio());
        prescripcion.setFechaFin(prescripcionDTO.getFechaFin());
        prescripcion.setInstrucciones(prescripcionDTO.getInstrucciones());
        final Cita cita = prescripcionDTO.getCita() == null ? null : citaRepository.findById(prescripcionDTO.getCita())
                .orElseThrow(() -> new NotFoundException("cita not found"));
        prescripcion.setCita(cita);
        final Medicamento medicamento = prescripcionDTO.getMedicamento() == null ? null : medicamentoRepository.findById(prescripcionDTO.getMedicamento())
                .orElseThrow(() -> new NotFoundException("medicamento not found"));
        prescripcion.setMedicamento(medicamento);
        return prescripcion;
    }

    public ReferencedWarning getReferencedWarning(final Integer idPrescripcion) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Prescripcion prescripcion = prescripcionRepository.findById(idPrescripcion)
                .orElseThrow(NotFoundException::new);
        final ConsumoMedicamento prescripcionConsumoMedicamento = consumoMedicamentoRepository.findFirstByPrescripcion(prescripcion);
        if (prescripcionConsumoMedicamento != null) {
            referencedWarning.setKey("prescripcion.consumoMedicamento.prescripcion.referenced");
            referencedWarning.addParam(prescripcionConsumoMedicamento.getIdConsumo());
            return referencedWarning;
        }
        return null;
    }

}
