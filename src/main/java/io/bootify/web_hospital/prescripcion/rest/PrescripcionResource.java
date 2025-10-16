package io.bootify.web_hospital.prescripcion.rest;

import io.bootify.web_hospital.prescripcion.model.PrescripcionDTO;
import io.bootify.web_hospital.prescripcion.service.PrescripcionService;
import io.bootify.web_hospital.util.ReferencedException;
import io.bootify.web_hospital.util.ReferencedWarning;
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
@RequestMapping(value = "/api/prescripcions", produces = MediaType.APPLICATION_JSON_VALUE)
public class PrescripcionResource {

    private final PrescripcionService prescripcionService;

    public PrescripcionResource(final PrescripcionService prescripcionService) {
        this.prescripcionService = prescripcionService;
    }

    @GetMapping
    public ResponseEntity<List<PrescripcionDTO>> getAllPrescripcions() {
        return ResponseEntity.ok(prescripcionService.findAll());
    }

    @GetMapping("/{idPrescripcion}")
    public ResponseEntity<PrescripcionDTO> getPrescripcion(
            @PathVariable(name = "idPrescripcion") final Integer idPrescripcion) {
        return ResponseEntity.ok(prescripcionService.get(idPrescripcion));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createPrescripcion(
            @RequestBody @Valid final PrescripcionDTO prescripcionDTO) {
        final Integer createdIdPrescripcion = prescripcionService.create(prescripcionDTO);
        return new ResponseEntity<>(createdIdPrescripcion, HttpStatus.CREATED);
    }

    @PutMapping("/{idPrescripcion}")
    public ResponseEntity<Integer> updatePrescripcion(
            @PathVariable(name = "idPrescripcion") final Integer idPrescripcion,
            @RequestBody @Valid final PrescripcionDTO prescripcionDTO) {
        prescripcionService.update(idPrescripcion, prescripcionDTO);
        return ResponseEntity.ok(idPrescripcion);
    }

    @DeleteMapping("/{idPrescripcion}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deletePrescripcion(
            @PathVariable(name = "idPrescripcion") final Integer idPrescripcion) {
        final ReferencedWarning referencedWarning = prescripcionService.getReferencedWarning(idPrescripcion);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        prescripcionService.delete(idPrescripcion);
        return ResponseEntity.noContent().build();
    }

}
