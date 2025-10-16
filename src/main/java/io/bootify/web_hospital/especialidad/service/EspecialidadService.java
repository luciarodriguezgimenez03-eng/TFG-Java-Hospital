package io.bootify.web_hospital.especialidad.service;

import io.bootify.web_hospital.especialidad.domain.Especialidad;
import io.bootify.web_hospital.especialidad.model.EspecialidadDTO;
import io.bootify.web_hospital.especialidad.repos.EspecialidadRepository;
import io.bootify.web_hospital.usuario.repos.UsuarioRepository;
import io.bootify.web_hospital.util.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class EspecialidadService {

    private final EspecialidadRepository especialidadRepository;
    private final UsuarioRepository usuarioRepository;

    public EspecialidadService(final EspecialidadRepository especialidadRepository,
            final UsuarioRepository usuarioRepository) {
        this.especialidadRepository = especialidadRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<EspecialidadDTO> findAll() {
        final List<Especialidad> especialidads = especialidadRepository.findAll(Sort.by("idEspecialidad"));
        return especialidads.stream()
                .map(especialidad -> mapToDTO(especialidad, new EspecialidadDTO()))
                .toList();
    }

    public EspecialidadDTO get(final Integer idEspecialidad) {
        return especialidadRepository.findById(idEspecialidad)
                .map(especialidad -> mapToDTO(especialidad, new EspecialidadDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final EspecialidadDTO especialidadDTO) {
        final Especialidad especialidad = new Especialidad();
        mapToEntity(especialidadDTO, especialidad);
        return especialidadRepository.save(especialidad).getIdEspecialidad();
    }

    public void update(final Integer idEspecialidad, final EspecialidadDTO especialidadDTO) {
        final Especialidad especialidad = especialidadRepository.findById(idEspecialidad)
                .orElseThrow(NotFoundException::new);
        mapToEntity(especialidadDTO, especialidad);
        especialidadRepository.save(especialidad);
    }

    public void delete(final Integer idEspecialidad) {
        final Especialidad especialidad = especialidadRepository.findById(idEspecialidad)
                .orElseThrow(NotFoundException::new);
        // remove many-to-many relations at owning side
        usuarioRepository.findAllByUsuariosEspecialidadeEspecialidades(especialidad)
                .forEach(usuario -> usuario.getUsuariosEspecialidadeEspecialidades().remove(especialidad));
        especialidadRepository.delete(especialidad);
    }

    private EspecialidadDTO mapToDTO(final Especialidad especialidad,
            final EspecialidadDTO especialidadDTO) {
        especialidadDTO.setIdEspecialidad(especialidad.getIdEspecialidad());
        especialidadDTO.setNombreEspecialidad(especialidad.getNombreEspecialidad());
        especialidadDTO.setDescripcion(especialidad.getDescripcion());
        return especialidadDTO;
    }

    private Especialidad mapToEntity(final EspecialidadDTO especialidadDTO,
            final Especialidad especialidad) {
        especialidad.setNombreEspecialidad(especialidadDTO.getNombreEspecialidad());
        especialidad.setDescripcion(especialidadDTO.getDescripcion());
        return especialidad;
    }

}
