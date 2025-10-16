package io.bootify.web_hospital.prescripcion.repos;

import io.bootify.web_hospital.cita.domain.Cita;
import io.bootify.web_hospital.prescripcion.domain.Prescripcion;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PrescripcionRepository extends JpaRepository<Prescripcion, Integer> {

    Prescripcion findFirstByCita(Cita cita);

}
