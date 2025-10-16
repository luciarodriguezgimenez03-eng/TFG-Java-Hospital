package io.bootify.web_hospital.cita.repos;

import io.bootify.web_hospital.cita.domain.Cita;
import io.bootify.web_hospital.sala.domain.Sala;
import io.bootify.web_hospital.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CitaRepository extends JpaRepository<Cita, Integer> {

    Cita findFirstByPaciente(Usuario usuario);

    Cita findFirstByEmpleado(Usuario usuario);

    Cita findFirstBySala(Sala sala);

}
