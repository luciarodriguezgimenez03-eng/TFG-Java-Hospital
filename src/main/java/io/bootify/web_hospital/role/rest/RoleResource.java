package io.bootify.web_hospital.role.rest;

import io.bootify.web_hospital.role.model.RoleDTO;
import io.bootify.web_hospital.role.service.RoleService;
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
@RequestMapping(value = "/api/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleResource {

    private final RoleService roleService;

    public RoleResource(final RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public ResponseEntity<List<RoleDTO>> getAllRoles() {
        return ResponseEntity.ok(roleService.findAll());
    }

    @GetMapping("/{idRol}")
    public ResponseEntity<RoleDTO> getRole(@PathVariable(name = "idRol") final Integer idRol) {
        return ResponseEntity.ok(roleService.get(idRol));
    }

    @PostMapping
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Integer> createRole(@RequestBody @Valid final RoleDTO roleDTO) {
        final Integer createdIdRol = roleService.create(roleDTO);
        return new ResponseEntity<>(createdIdRol, HttpStatus.CREATED);
    }

    @PutMapping("/{idRol}")
    public ResponseEntity<Integer> updateRole(@PathVariable(name = "idRol") final Integer idRol,
            @RequestBody @Valid final RoleDTO roleDTO) {
        roleService.update(idRol, roleDTO);
        return ResponseEntity.ok(idRol);
    }

    @DeleteMapping("/{idRol}")
    @ApiResponse(responseCode = "204")
    public ResponseEntity<Void> deleteRole(@PathVariable(name = "idRol") final Integer idRol) {
        roleService.delete(idRol);
        return ResponseEntity.noContent().build();
    }

}
