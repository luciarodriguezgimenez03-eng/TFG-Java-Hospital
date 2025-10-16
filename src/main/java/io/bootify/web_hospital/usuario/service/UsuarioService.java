package io.bootify.web_hospital.usuario.service;

import io.bootify.web_hospital.cita.domain.Cita;
import io.bootify.web_hospital.cita.repos.CitaRepository;
import io.bootify.web_hospital.consumo_medicamento.domain.ConsumoMedicamento;
import io.bootify.web_hospital.consumo_medicamento.repos.ConsumoMedicamentoRepository;
import io.bootify.web_hospital.especialidad.domain.Especialidad;
import io.bootify.web_hospital.especialidad.repos.EspecialidadRepository;
import io.bootify.web_hospital.role.domain.Role;
import io.bootify.web_hospital.role.repos.RoleRepository;
import io.bootify.web_hospital.usuario.domain.Usuario;
import io.bootify.web_hospital.usuario.model.UsuarioDTO;
import io.bootify.web_hospital.usuario.repos.UsuarioRepository;
import io.bootify.web_hospital.util.NotFoundException;
import io.bootify.web_hospital.util.ReferencedWarning;
import jakarta.transaction.Transactional;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final EspecialidadRepository especialidadRepository;
    private final CitaRepository citaRepository;
    private final ConsumoMedicamentoRepository consumoMedicamentoRepository;

    public UsuarioService(final UsuarioRepository usuarioRepository,
            final RoleRepository roleRepository,
            final EspecialidadRepository especialidadRepository,
            final CitaRepository citaRepository,
            final ConsumoMedicamentoRepository consumoMedicamentoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.especialidadRepository = especialidadRepository;
        this.citaRepository = citaRepository;
        this.consumoMedicamentoRepository = consumoMedicamentoRepository;
    }

    public List<UsuarioDTO> findAll() {
        final List<Usuario> usuarios = usuarioRepository.findAll(Sort.by("idUsuario"));
        return usuarios.stream()
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .toList();
    }

    public UsuarioDTO get(final Integer idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .map(usuario -> mapToDTO(usuario, new UsuarioDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final UsuarioDTO usuarioDTO) {
        final Usuario usuario = new Usuario();
        mapToEntity(usuarioDTO, usuario);
        return usuarioRepository.save(usuario).getIdUsuario();
    }

    public void update(final Integer idUsuario, final UsuarioDTO usuarioDTO) {
        final Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(NotFoundException::new);
        mapToEntity(usuarioDTO, usuario);
        usuarioRepository.save(usuario);
    }

    public void delete(final Integer idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }

    private UsuarioDTO mapToDTO(final Usuario usuario, final UsuarioDTO usuarioDTO) {
        usuarioDTO.setIdUsuario(usuario.getIdUsuario());
        usuarioDTO.setDni(usuario.getDni());
        usuarioDTO.setNombre(usuario.getNombre());
        usuarioDTO.setApellido(usuario.getApellido());
        usuarioDTO.setEmail(usuario.getEmail());
        usuarioDTO.setContraseAHash(usuario.getContraseAHash());
        usuarioDTO.setUrlImagen(usuario.getUrlImagen());
        usuarioDTO.setTelefono(usuario.getTelefono());
        usuarioDTO.setDireccion(usuario.getDireccion());
        usuarioDTO.setFechaNacimiento(usuario.getFechaNacimiento());
        usuarioDTO.setGenero(usuario.getGenero());
        usuarioDTO.setTipoSangre(usuario.getTipoSangre());
        usuarioDTO.setAlergias(usuario.getAlergias());
        usuarioDTO.setFechaCreacion(usuario.getFechaCreacion());
        usuarioDTO.setFechaModificacion(usuario.getFechaModificacion());
        usuarioDTO.setUsuariosRoleRoles(usuario.getUsuariosRoleRoles().stream()
                .map(role -> role.getIdRol())
                .toList());
        usuarioDTO.setUsuariosEspecialidadeEspecialidades(usuario.getUsuariosEspecialidadeEspecialidades().stream()
                .map(especialidad -> especialidad.getIdEspecialidad())
                .toList());
        return usuarioDTO;
    }

    private Usuario mapToEntity(final UsuarioDTO usuarioDTO, final Usuario usuario) {
        usuario.setDni(usuarioDTO.getDni());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setContraseAHash(usuarioDTO.getContraseAHash());
        usuario.setUrlImagen(usuarioDTO.getUrlImagen());
        usuario.setTelefono(usuarioDTO.getTelefono());
        usuario.setDireccion(usuarioDTO.getDireccion());
        usuario.setFechaNacimiento(usuarioDTO.getFechaNacimiento());
        usuario.setGenero(usuarioDTO.getGenero());
        usuario.setTipoSangre(usuarioDTO.getTipoSangre());
        usuario.setAlergias(usuarioDTO.getAlergias());
        usuario.setFechaCreacion(usuarioDTO.getFechaCreacion());
        usuario.setFechaModificacion(usuarioDTO.getFechaModificacion());
        final List<Role> usuariosRoleRoles = roleRepository.findAllById(
                usuarioDTO.getUsuariosRoleRoles() == null ? Collections.emptyList() : usuarioDTO.getUsuariosRoleRoles());
        if (usuariosRoleRoles.size() != (usuarioDTO.getUsuariosRoleRoles() == null ? 0 : usuarioDTO.getUsuariosRoleRoles().size())) {
            throw new NotFoundException("one of usuariosRoleRoles not found");
        }
        usuario.setUsuariosRoleRoles(new HashSet<>(usuariosRoleRoles));
        final List<Especialidad> usuariosEspecialidadeEspecialidades = especialidadRepository.findAllById(
                usuarioDTO.getUsuariosEspecialidadeEspecialidades() == null ? Collections.emptyList() : usuarioDTO.getUsuariosEspecialidadeEspecialidades());
        if (usuariosEspecialidadeEspecialidades.size() != (usuarioDTO.getUsuariosEspecialidadeEspecialidades() == null ? 0 : usuarioDTO.getUsuariosEspecialidadeEspecialidades().size())) {
            throw new NotFoundException("one of usuariosEspecialidadeEspecialidades not found");
        }
        usuario.setUsuariosEspecialidadeEspecialidades(new HashSet<>(usuariosEspecialidadeEspecialidades));
        return usuario;
    }

    public boolean dniExists(final String dni) {
        return usuarioRepository.existsByDniIgnoreCase(dni);
    }

    public boolean emailExists(final String email) {
        return usuarioRepository.existsByEmailIgnoreCase(email);
    }

    public ReferencedWarning getReferencedWarning(final Integer idUsuario) {
        final ReferencedWarning referencedWarning = new ReferencedWarning();
        final Usuario usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(NotFoundException::new);
        final Cita pacienteCita = citaRepository.findFirstByPaciente(usuario);
        if (pacienteCita != null) {
            referencedWarning.setKey("usuario.cita.paciente.referenced");
            referencedWarning.addParam(pacienteCita.getIdCita());
            return referencedWarning;
        }
        final Cita empleadoCita = citaRepository.findFirstByEmpleado(usuario);
        if (empleadoCita != null) {
            referencedWarning.setKey("usuario.cita.empleado.referenced");
            referencedWarning.addParam(empleadoCita.getIdCita());
            return referencedWarning;
        }
        final ConsumoMedicamento pacienteConsumoMedicamento = consumoMedicamentoRepository.findFirstByPaciente(usuario);
        if (pacienteConsumoMedicamento != null) {
            referencedWarning.setKey("usuario.consumoMedicamento.paciente.referenced");
            referencedWarning.addParam(pacienteConsumoMedicamento.getIdConsumo());
            return referencedWarning;
        }
        return null;
    }

}
