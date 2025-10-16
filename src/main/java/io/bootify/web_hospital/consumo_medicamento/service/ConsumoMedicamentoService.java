package io.bootify.web_hospital.consumo_medicamento.service;

import io.bootify.web_hospital.consumo_medicamento.domain.ConsumoMedicamento;
import io.bootify.web_hospital.consumo_medicamento.model.ConsumoMedicamentoDTO;
import io.bootify.web_hospital.consumo_medicamento.repos.ConsumoMedicamentoRepository;
import io.bootify.web_hospital.prescripcion.domain.Prescripcion;
import io.bootify.web_hospital.prescripcion.repos.PrescripcionRepository;
import io.bootify.web_hospital.usuario.domain.Usuario;
import io.bootify.web_hospital.usuario.repos.UsuarioRepository;
import io.bootify.web_hospital.util.NotFoundException;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ConsumoMedicamentoService {

    private final ConsumoMedicamentoRepository consumoMedicamentoRepository;
    private final PrescripcionRepository prescripcionRepository;
    private final UsuarioRepository usuarioRepository;

    public ConsumoMedicamentoService(
            final ConsumoMedicamentoRepository consumoMedicamentoRepository,
            final PrescripcionRepository prescripcionRepository,
            final UsuarioRepository usuarioRepository) {
        this.consumoMedicamentoRepository = consumoMedicamentoRepository;
        this.prescripcionRepository = prescripcionRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<ConsumoMedicamentoDTO> findAll() {
        final List<ConsumoMedicamento> consumoMedicamentoes = consumoMedicamentoRepository.findAll(Sort.by("idConsumo"));
        return consumoMedicamentoes.stream()
                .map(consumoMedicamento -> mapToDTO(consumoMedicamento, new ConsumoMedicamentoDTO()))
                .toList();
    }

    public ConsumoMedicamentoDTO get(final Integer idConsumo) {
        return consumoMedicamentoRepository.findById(idConsumo)
                .map(consumoMedicamento -> mapToDTO(consumoMedicamento, new ConsumoMedicamentoDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final ConsumoMedicamentoDTO consumoMedicamentoDTO) {
        final ConsumoMedicamento consumoMedicamento = new ConsumoMedicamento();
        mapToEntity(consumoMedicamentoDTO, consumoMedicamento);
        return consumoMedicamentoRepository.save(consumoMedicamento).getIdConsumo();
    }

    public void update(final Integer idConsumo, final ConsumoMedicamentoDTO consumoMedicamentoDTO) {
        final ConsumoMedicamento consumoMedicamento = consumoMedicamentoRepository.findById(idConsumo)
                .orElseThrow(NotFoundException::new);
        mapToEntity(consumoMedicamentoDTO, consumoMedicamento);
        consumoMedicamentoRepository.save(consumoMedicamento);
    }

    public void delete(final Integer idConsumo) {
        consumoMedicamentoRepository.deleteById(idConsumo);
    }

    private ConsumoMedicamentoDTO mapToDTO(final ConsumoMedicamento consumoMedicamento,
            final ConsumoMedicamentoDTO consumoMedicamentoDTO) {
        consumoMedicamentoDTO.setIdConsumo(consumoMedicamento.getIdConsumo());
        consumoMedicamentoDTO.setFechaHoraConsumo(consumoMedicamento.getFechaHoraConsumo());
        consumoMedicamentoDTO.setConfirmacion(consumoMedicamento.getConfirmacion());
        consumoMedicamentoDTO.setPrescripcion(consumoMedicamento.getPrescripcion() == null ? null : consumoMedicamento.getPrescripcion().getIdPrescripcion());
        consumoMedicamentoDTO.setPaciente(consumoMedicamento.getPaciente() == null ? null : consumoMedicamento.getPaciente().getIdUsuario());
        return consumoMedicamentoDTO;
    }

    private ConsumoMedicamento mapToEntity(final ConsumoMedicamentoDTO consumoMedicamentoDTO,
            final ConsumoMedicamento consumoMedicamento) {
        consumoMedicamento.setFechaHoraConsumo(consumoMedicamentoDTO.getFechaHoraConsumo());
        consumoMedicamento.setConfirmacion(consumoMedicamentoDTO.getConfirmacion());
        final Prescripcion prescripcion = consumoMedicamentoDTO.getPrescripcion() == null ? null : prescripcionRepository.findById(consumoMedicamentoDTO.getPrescripcion())
                .orElseThrow(() -> new NotFoundException("prescripcion not found"));
        consumoMedicamento.setPrescripcion(prescripcion);
        final Usuario paciente = consumoMedicamentoDTO.getPaciente() == null ? null : usuarioRepository.findById(consumoMedicamentoDTO.getPaciente())
                .orElseThrow(() -> new NotFoundException("paciente not found"));
        consumoMedicamento.setPaciente(paciente);
        return consumoMedicamento;
    }

}
