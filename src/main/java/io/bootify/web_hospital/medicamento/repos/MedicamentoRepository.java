package io.bootify.web_hospital.medicamento.repos;

import io.bootify.web_hospital.medicamento.domain.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MedicamentoRepository extends JpaRepository<Medicamento, Integer> {
}
