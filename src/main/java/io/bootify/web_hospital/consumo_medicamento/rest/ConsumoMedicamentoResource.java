package io.bootify.web_hospital.consumo_medicamento.rest;

import io.bootify.web_hospital.consumo_medicamento.model.ConsumoMedicamentoDTO;
import io.bootify.web_hospital.consumo_medicamento.service.ConsumoMedicamentoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/consumoMedicamentos", produces = MediaType.APPLICATION_JSON_VALUE)
public class ConsumoMedicamentoResource {

    private final ConsumoMedicamentoService consumoMedicamentoService;

    public ConsumoMedicamentoResource(final ConsumoMedicamentoService consumoMedicamentoService) {
        this.consumoMedicamentoService = consumoMedicamentoService;
    }

    @GetMapping
    public ResponseEntity<List<ConsumoMedicamentoDTO>> getAllConsumoMedicamentos() {
        return ResponseEntity.ok(consumoMedicamentoService.findAll());
    }

    @GetMapping("/{idConsumo}")
    public ResponseEntity<ConsumoMedicamentoDTO> getConsumoMedicamento(
            @PathVariable(name = "idConsumo") final Integer idConsumo) {
        return ResponseEntity.ok(consumoMedicamentoService.get(idConsumo));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createConsumoMedicamento(
            @RequestBody @Valid final ConsumoMedicamentoDTO consumoMedicamentoDTO) {
        final Integer createdIdConsumo = consumoMedicamentoService.create(consumoMedicamentoDTO);
        return new ResponseEntity<>(createdIdConsumo, HttpStatus.CREATED);
    }

    @PutMapping("/{idConsumo}")
    public ResponseEntity<Integer> updateConsumoMedicamento(
            @PathVariable(name = "idConsumo") final Integer idConsumo,
            @RequestBody @Valid final ConsumoMedicamentoDTO consumoMedicamentoDTO) {
        consumoMedicamentoService.update(idConsumo, consumoMedicamentoDTO);
        return ResponseEntity.ok(idConsumo);
    }

    @DeleteMapping("/{idConsumo}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteConsumoMedicamento(
            @PathVariable(name = "idConsumo") final Integer idConsumo) {
        consumoMedicamentoService.delete(idConsumo);
        return ResponseEntity.noContent().build();
    }

}
