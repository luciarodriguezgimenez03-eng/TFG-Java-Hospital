package io.bootify.web_hospital.usuario.repos;

import io.bootify.web_hospital.especialidad.domain.Especialidad;
import io.bootify.web_hospital.role.domain.Role;
import io.bootify.web_hospital.usuario.domain.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findFirstByUsuariosRoleRoles(Role role);

    Usuario findFirstByUsuariosEspecialidadeEspecialidades(Especialidad especialidad);

    List<Usuario> findAllByUsuariosRoleRoles(Role role);

    List<Usuario> findAllByUsuariosEspecialidadeEspecialidades(Especialidad especialidad);

    boolean existsByDniIgnoreCase(String dni);

    boolean existsByEmailIgnoreCase(String email);

}
