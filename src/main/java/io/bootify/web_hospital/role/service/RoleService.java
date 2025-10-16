package io.bootify.web_hospital.role.service;

import io.bootify.web_hospital.role.domain.Role;
import io.bootify.web_hospital.role.model.RoleDTO;
import io.bootify.web_hospital.role.repos.RoleRepository;
import io.bootify.web_hospital.usuario.repos.UsuarioRepository;
import io.bootify.web_hospital.util.NotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class RoleService {

    private final RoleRepository roleRepository;
    private final UsuarioRepository usuarioRepository;

    public RoleService(final RoleRepository roleRepository,
            final UsuarioRepository usuarioRepository) {
        this.roleRepository = roleRepository;
        this.usuarioRepository = usuarioRepository;
    }

    public List<RoleDTO> findAll() {
        final List<Role> roles = roleRepository.findAll(Sort.by("idRol"));
        return roles.stream()
                .map(role -> mapToDTO(role, new RoleDTO()))
                .toList();
    }

    public RoleDTO get(final Integer idRol) {
        return roleRepository.findById(idRol)
                .map(role -> mapToDTO(role, new RoleDTO()))
                .orElseThrow(NotFoundException::new);
    }

    public Integer create(final RoleDTO roleDTO) {
        final Role role = new Role();
        mapToEntity(roleDTO, role);
        return roleRepository.save(role).getIdRol();
    }

    public void update(final Integer idRol, final RoleDTO roleDTO) {
        final Role role = roleRepository.findById(idRol)
                .orElseThrow(NotFoundException::new);
        mapToEntity(roleDTO, role);
        roleRepository.save(role);
    }

    public void delete(final Integer idRol) {
        final Role role = roleRepository.findById(idRol)
                .orElseThrow(NotFoundException::new);
        // remove many-to-many relations at owning side
        usuarioRepository.findAllByUsuariosRoleRoles(role)
                .forEach(usuario -> usuario.getUsuariosRoleRoles().remove(role));
        roleRepository.delete(role);
    }

    private RoleDTO mapToDTO(final Role role, final RoleDTO roleDTO) {
        roleDTO.setIdRol(role.getIdRol());
        roleDTO.setNombreRol(role.getNombreRol());
        return roleDTO;
    }

    private Role mapToEntity(final RoleDTO roleDTO, final Role role) {
        role.setNombreRol(roleDTO.getNombreRol());
        return role;
    }

}
