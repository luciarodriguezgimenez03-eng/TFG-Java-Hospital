package io.bootify.web_hospital.sala.rest;

import io.bootify.web_hospital.sala.model.SalaDTO;
import io.bootify.web_hospital.sala.service.SalaService;
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
@RequestMapping(value = "/api/salas", produces = MediaType.APPLICATION_JSON_VALUE)
public class SalaResource {

    private final SalaService salaService;

    public SalaResource(final SalaService salaService) {
        this.salaService = salaService;
    }

    @GetMapping
    public ResponseEntity<List<SalaDTO>> getAllSalas() {
        return ResponseEntity.ok(salaService.findAll());
    }

    @GetMapping("/{idSala}")
    public ResponseEntity<SalaDTO> getSala(@PathVariable(name = "idSala") final Integer idSala) {
        return ResponseEntity.ok(salaService.get(idSala));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createSala(@RequestBody @Valid final SalaDTO salaDTO) {
        final Integer createdIdSala = salaService.create(salaDTO);
        return new ResponseEntity<>(createdIdSala, HttpStatus.CREATED);
    }

    @PutMapping("/{idSala}")
    public ResponseEntity<Integer> updateSala(@PathVariable(name = "idSala") final Integer idSala,
            @RequestBody @Valid final SalaDTO salaDTO) {
        salaService.update(idSala, salaDTO);
        return ResponseEntity.ok(idSala);
    }

    @DeleteMapping("/{idSala}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteSala(@PathVariable(name = "idSala") final Integer idSala) {
        final ReferencedWarning referencedWarning = salaService.getReferencedWarning(idSala);
        if (referencedWarning != null) {
            throw new ReferencedException(referencedWarning);
        }
        salaService.delete(idSala);
        return ResponseEntity.noContent().build();
    }

}
