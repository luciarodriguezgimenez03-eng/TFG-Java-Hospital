package io.bootify.web_hospital.sala.service;

import io.bootify.web_hospital.cita.domain.Cita;
import io.bootify.web_hospital.cita.repos.CitaRepository;
import io.bootify.web_hospital.sala.domain.Sala;
import io.bootify.web_hospital.sala.model.SalaDTO;
import io.bootify.web_hospital.sala.repos.SalaRepository;
import io.bootify.web_hospital.util.NotFoundException;
import io.bootify.web_hospital.util.ReferencedWarning;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class SalaService {

    private final SalaRepository salaRepository;
    private final CitaRepository citaRepository;

    public SalaService(final SalaRepository salaRepository, final CitaRepository citaRepository) {
        this.salaRepository = salaRepository;
        this.citaRepository = citaRepository;
    }

    public List<SalaDTO> findAll() {
        final List<Sala> salas = salaRepository.findAll(Sort.by("idSala"));
        return salas.stream()
                .map(sala -> mapToDTO(sala, new SalaDTO()))
                .toList();
    }

    public SalaDTO get(final Integer idSala) {
        return salaRepository.findById(idSala)
                .map(sala -> mapToDTO(sala, new SalaDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final SalaDTO salaDTO) {
        final Sala sala = new Sala();
        mapToEntity(salaDTO, sala);
        return salaRepository.save(sala).getIdSala();
    }

    public void update(final Integer idSala, final SalaDTO salaDTO) {
        final Sala sala = salaRepository.findById(idSala)
                .orElseThrow(NotFoundException::new);
        mapToEntity(salaDTO, sala);
        salaRepository.save(sala);
    }

    public void delete(final Integer idSala) {
        salaRepository.deleteById(idSala);
    }

    private SalaDTO mapToDTO(final Sala sala, final SalaDTO salaDTO) {
        salaDTO.setIdSala(sala.getIdSala());
        salaDTO.setDisponibilidad(sala.getDisponibilidad());
        salaDTO.setNombreSala(sala.getNombreSala());
        salaDTO.setNumeroSala(sala.getNumeroSala());
        salaDTO.setPlantaSala(sala.getPlantaSala());
        return salaDTO;
    }

    private Sala mapToEntity(final SalaDTO salaDTO, final Sala sala) {
        sala.setDisponibilidad(salaDTO.getDisponibilidad());
        sala.setNombreSala(salaDTO.getNombreSala());
        sala.setNumeroSala(salaDTO.getNumeroSala());
        sala.setPlantaSala(salaDTO.getPlantaSala());
        return sala;
    }

    public ReferencedWarning getReferencedWarning(final Integer idSala) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Sala sala = salaRepository.findById(idSala)
                .orElseThrow(NotFoundException::new);
        final Cita salaCita = citaRepository.findFirstBySala(sala);
        if (salaCita != null) {
            referencedWarning.setKey("sala.cita.sala.referenced");
            referencedWarning.addParam(salaCita.getIdCita());
            return referencedWarning;
        }
        return null;
    }

}
