package io.bootify.web_hospital.cita.service;

import io.bootify.web_hospital.cita.domain.Cita;
import io.bootify.web_hospital.cita.model.CitaDTO;
import io.bootify.web_hospital.cita.repos.CitaRepository;
import io.bootify.web_hospital.prescripcion.domain.Prescripcion;
import io.bootify.web_hospital.prescripcion.repos.PrescripcionRepository;
import io.bootify.web_hospital.sala.domain.Sala;
import io.bootify.web_hospital.sala.repos.SalaRepository;
import io.bootify.web_hospital.usuario.domain.Usuario;
import io.bootify.web_hospital.usuario.repos.UsuarioRepository;
import io.bootify.web_hospital.util.NotFoundException;
import io.bootify.web_hospital.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class CitaService {

    private final CitaRepository citaRepository;
    private final UsuarioRepository usuarioRepository;
    private final SalaRepository salaRepository;
    private final PrescripcionRepository prescripcionRepository;

    public CitaService(final CitaRepository citaRepository,
            final UsuarioRepository usuarioRepository, final SalaRepository salaRepository,
            final PrescripcionRepository prescripcionRepository) {
        this.citaRepository = citaRepository;
        this.usuarioRepository = usuarioRepository;
        this.salaRepository = salaRepository;
        this.prescripcionRepository = prescripcionRepository;
    }

    public List<CitaDTO> findAll() {
        final List<Cita> citas = citaRepository.findAll(Sort.by("idCita"));
        return citas.stream()
                .map(cita -> mapToDTO(cita, new CitaDTO()))
                .toList();
    }

    public CitaDTO get(final Integer idCita) {
        return citaRepository.findById(idCita)
                .map(cita -> mapToDTO(cita, new CitaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final CitaDTO citaDTO) {
        final Cita cita = new Cita();
        mapToEntity(citaDTO, cita);
        return citaRepository.save(cita).getIdCita();
    }

    public void update(final Integer idCita, final CitaDTO citaDTO) {
        final Cita cita = citaRepository.findById(idCita)
                .orElseThrow(NotFoundException::new);
        mapToEntity(citaDTO, cita);
        citaRepository.save(cita);
    }

    public void delete(final Integer idCita) {
        citaRepository.deleteById(idCita);
    }

    private CitaDTO mapToDTO(final Cita cita, final CitaDTO citaDTO) {
        citaDTO.setIdCita(cita.getIdCita());
        citaDTO.setDescripcion(cita.getDescripcion());
        citaDTO.setFechaCita(cita.getFechaCita());
        citaDTO.setHoraCita(cita.getHoraCita());
        citaDTO.setPaciente(cita.getPaciente() == null ? null : cita.getPaciente().getIdUsuario());
        citaDTO.setEmpleado(cita.getEmpleado() == null ? null : cita.getEmpleado().getIdUsuario());
        citaDTO.setSala(cita.getSala() == null ? null : cita.getSala().getIdSala());
        return citaDTO;
    }

    private Cita mapToEntity(final CitaDTO citaDTO, final Cita cita) {
        cita.setDescripcion(citaDTO.getDescripcion());
        cita.setFechaCita(citaDTO.getFechaCita());
        cita.setHoraCita(citaDTO.getHoraCita());
        final Usuario paciente = citaDTO.getPaciente() == null ? null : usuarioRepository.findById(citaDTO.getPaciente())
                .orElseThrow(() -> new NotFoundException("paciente not found"));
        cita.setPaciente(paciente);
        final Usuario empleado = citaDTO.getEmpleado() == null ? null : usuarioRepository.findById(citaDTO.getEmpleado())
                .orElseThrow(() -> new NotFoundException("empleado not found"));
        cita.setEmpleado(empleado);
        final Sala sala = citaDTO.getSala() == null ? null : salaRepository.findById(citaDTO.getSala())
                .orElseThrow(() -> new NotFoundException("sala not found"));
        cita.setSala(sala);
        return cita;
    }

    public ReferencedWarning getReferencedWarning(final Integer idCita) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Cita cita = citaRepository.findById(idCita)
                .orElseThrow(NotFoundException::new);
        final Prescripcion citaPrescripcion = prescripcionRepository.findFirstByCita(cita);
        if (citaPrescripcion != null) {
            referencedWarning.setKey("cita.prescripcion.cita.referenced");
            referencedWarning.addParam(citaPrescripcion.getIdPrescripcion());
            return referencedWarning;
        }
        return null;
    }

}
