package io.bootify.web_hospital.consumo_medicamento.repos;

import io.bootify.web_hospital.consumo_medicamento.domain.ConsumoMedicamento;
import io.bootify.web_hospital.prescripcion.domain.Prescripcion;
import io.bootify.web_hospital.usuario.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ConsumoMedicamentoRepository extends JpaRepository<ConsumoMedicamento, Integer> {

    ConsumoMedicamento findFirstByPrescripcion(Prescripcion prescripcion);

    ConsumoMedicamento findFirstByPaciente(Usuario usuario);

}
