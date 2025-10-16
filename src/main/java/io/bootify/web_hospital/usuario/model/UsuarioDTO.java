package io.bootify.web_hospital.usuario.model;

import io.bootify.web_hospital.validation.FechaNacimiento;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;


public class UsuarioDTO {

    private Integer idUsuario;

    @NotNull
    @Size(max = 255)
    @UsuarioDniUnique
    private String dni;

    @NotNull
    @Size(max = 100)
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü\\s]+$", message = "El nombre solo puede contener letras, tildes y espacios.")
    private String nombre;

    @NotNull
    @Size(max = 100)
    @Pattern(regexp = "^[A-Za-zÁÉÍÓÚáéíóúÑñÜü\\s]+$", message = "El apellido solo puede contener letras, tildes y espacios.")
    private String apellido;

    @NotNull
    @Size(max = 255)
    @UsuarioEmailUnique
    @Email(message = "El email debe ser válido.")
    private String email;

    @NotNull
    @Size(max = 255)
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres.")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{8,}$", message = "La contraseña debe contener al menos una letra mayúscula, un número y un símbolo especial.")
    private String contraseAHash;

    @Size(max = 255)
    private String urlImagen;

    @NotNull
    @Pattern(regexp = "^\\d{9}$", message = "El teléfono debe contener exactamente 9 dígitos.")
    private String telefono;

    @NotNull
    @Size(max = 255)
    private String direccion;

    @NotNull
    @FechaNacimiento
    private LocalDate fechaNacimiento;

    @NotNull
    @Size(max = 50)
    private String genero;

    @Size(max = 3)
    private String tipoSangre;

    @Size(max = 255)
    private String alergias;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime fechaCreacion;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssXXX")
    private OffsetDateTime fechaModificacion;

    private List<Integer> usuariosRoleRoles;

    private List<Integer> usuariosEspecialidadeEspecialidades;

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(final Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(final String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(final String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(final String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getContraseAHash() {
        return contraseAHash;
    }

    public void setContraseAHash(final String contraseAHash) {
        this.contraseAHash = contraseAHash;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(final String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(final String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(final String direccion) {
        this.direccion = direccion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(final LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(final String genero) {
        this.genero = genero;
    }

    public String getTipoSangre() {
        return tipoSangre;
    }

    public void setTipoSangre(final String tipoSangre) {
        this.tipoSangre = tipoSangre;
    }

    public String getAlergias() {
        return alergias;
    }

    public void setAlergias(final String alergias) {
        this.alergias = alergias;
    }

    public OffsetDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(final OffsetDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public OffsetDateTime getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(final OffsetDateTime fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    public List<Integer> getUsuariosRoleRoles() {
        return usuariosRoleRoles;
    }

    public void setUsuariosRoleRoles(final List<Integer> usuariosRoleRoles) {
        this.usuariosRoleRoles = usuariosRoleRoles;
    }

    public List<Integer> getUsuariosEspecialidadeEspecialidades() {
        return usuariosEspecialidadeEspecialidades;
    }

    public void setUsuariosEspecialidadeEspecialidades(
            final List<Integer> usuariosEspecialidadeEspecialidades) {
        this.usuariosEspecialidadeEspecialidades = usuariosEspecialidadeEspecialidades;
    }

}
