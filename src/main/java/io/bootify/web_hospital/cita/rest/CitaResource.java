package io.bootify.web_hospital.cita.rest;

import io.bootify.web_hospital.cita.model.CitaDTO;
import io.bootify.web_hospital.cita.service.CitaService;
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
@RequestMapping(value = "/api/citas", produces = MediaType.APPLICATION_JSON_VALUE)
public class CitaResource {

    private final CitaService citaService;

    public CitaResource(final CitaService citaService) {
        this.citaService = citaService;
    }

    @GetMapping
    public ResponseEntity<List<CitaDTO>> getAllCitas() {
        return ResponseEntity.ok(citaService.findAll());
    }

    @GetMapping("/{idCita}")
    public ResponseEntity<CitaDTO> getCita(@PathVariable(name = "idCita") final Integer idCita) {
        return ResponseEntity.ok(citaService.get(idCita));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createCita(@RequestBody @Valid final CitaDTO citaDTO) {
        final Integer createdIdCita = citaService.create(citaDTO);
        return new ResponseEntity<>(createdIdCita, HttpStatus.CREATED);
    }

    @PutMapping("/{idCita}")
    public ResponseEntity<Integer> updateCita(@PathVariable(name = "idCita") final Integer idCita,
            @RequestBody @Valid final CitaDTO citaDTO) {
        citaService.update(idCita, citaDTO);
        return ResponseEntity.ok(idCita);
    }

    @DeleteMapping("/{idCita}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteCita(@PathVariable(name = "idCita") final Integer idCita) {
        final ReferencedWarning referencedWarning = citaService.getReferencedWarning(idCita);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        citaService.delete(idCita);
        return ResponseEntity.noContent().build();
    }

}
