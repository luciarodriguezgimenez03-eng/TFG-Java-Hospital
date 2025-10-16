package io.bootify.web_hospital.especialidad.repos;

import io.bootify.web_hospital.especialidad.domain.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EspecialidadRepository extends JpaRepository<Especialidad, Integer> {
}
