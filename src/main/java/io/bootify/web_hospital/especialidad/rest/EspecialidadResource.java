package io.bootify.web_hospital.especialidad.rest;

import io.bootify.web_hospital.especialidad.model.EspecialidadDTO;
import io.bootify.web_hospital.especialidad.service.EspecialidadService;
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
@RequestMapping(value = "/api/especialidads", produces = MediaType.APPLICATION_JSON_VALUE)
public class EspecialidadResource {

    private final EspecialidadService especialidadService;

    public EspecialidadResource(final EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @GetMapping
    public ResponseEntity<List<EspecialidadDTO>> getAllEspecialidads() {
        return ResponseEntity.ok(especialidadService.findAll());
    }

    @GetMapping("/{idEspecialidad}")
    public ResponseEntity<EspecialidadDTO> getEspecialidad(
            @PathVariable(name = "idEspecialidad") final Integer idEspecialidad) {
        return ResponseEntity.ok(especialidadService.get(idEspecialidad));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createEspecialidad(
            @RequestBody @Valid final EspecialidadDTO especialidadDTO) {
        final Integer createdIdEspecialidad = especialidadService.create(especialidadDTO);
        return new ResponseEntity<>(createdIdEspecialidad, HttpStatus.CREATED);
    }

    @PutMapping("/{idEspecialidad}")
    public ResponseEntity<Integer> updateEspecialidad(
            @PathVariable(name = "idEspecialidad") final Integer idEspecialidad,
            @RequestBody @Valid final EspecialidadDTO especialidadDTO) {
        especialidadService.update(idEspecialidad, especialidadDTO);
        return ResponseEntity.ok(idEspecialidad);
    }

    @DeleteMapping("/{idEspecialidad}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteEspecialidad(
            @PathVariable(name = "idEspecialidad") final Integer idEspecialidad) {
        especialidadService.delete(idEspecialidad);
        return ResponseEntity.noContent().build();
    }

}
