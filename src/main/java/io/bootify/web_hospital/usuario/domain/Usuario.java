package io.bootify.web_hospital.usuario.domain;

import io.bootify.web_hospital.cita.domain.Cita;
import io.bootify.web_hospital.consumo_medicamento.domain.ConsumoMedicamento;
import io.bootify.web_hospital.especialidad.domain.Especialidad;
import io.bootify.web_hospital.role.domain.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.Set;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


@Entity
@EntityListeners(AuditingEntityListener.class)
public class Usuario {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;

    @Column(nullable = false, unique = true)
    private String dni;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String contraseAHash;

    @Column
    private String urlImagen;

    @Column(nullable = false, length = 15)
    private String telefono;

    @Column(nullable = false)
    private String direccion;

    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Column(nullable = false, length = 50)
    private String genero;

    @Column(length = 3)
    private String tipoSangre;

    @Column
    private String alergias;

    @Column
    private OffsetDateTime fechaCreacion;

    @Column
    private OffsetDateTime fechaModificacion;

    @ManyToMany
    @JoinTable(
            name = "UsuariosRole",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idRol")
    )
    private Set<Role> usuariosRoleRoles;

    @ManyToMany
    @JoinTable(
            name = "UsuariosEspecialidade",
            joinColumns = @JoinColumn(name = "idUsuario"),
            inverseJoinColumns = @JoinColumn(name = "idEspecialidad")
    )
    private Set<Especialidad> usuariosEspecialidadeEspecialidades;

    @OneToMany(mappedBy = "paciente")
    private Set<Cita> pacienteCitas;

    @OneToMany(mappedBy = "empleado")
    private Set<Cita> empleadoCitas;

    @OneToMany(mappedBy = "paciente")
    private Set<ConsumoMedicamento> pacienteConsumoMedicamentoes;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private OffsetDateTime dateCreated;

    @LastModifiedDate
    @Column(nullable = false)
    private OffsetDateTime lastUpdated;

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

    public Set<Role> getUsuariosRoleRoles() {
        return usuariosRoleRoles;
    }

    public void setUsuariosRoleRoles(final Set<Role> usuariosRoleRoles) {
        this.usuariosRoleRoles = usuariosRoleRoles;
    }

    public Set<Especialidad> getUsuariosEspecialidadeEspecialidades() {
        return usuariosEspecialidadeEspecialidades;
    }

    public void setUsuariosEspecialidadeEspecialidades(
            final Set<Especialidad> usuariosEspecialidadeEspecialidades) {
        this.usuariosEspecialidadeEspecialidades = usuariosEspecialidadeEspecialidades;
    }

    public Set<Cita> getPacienteCitas() {
        return pacienteCitas;
    }

    public void setPacienteCitas(final Set<Cita> pacienteCitas) {
        this.pacienteCitas = pacienteCitas;
    }

    public Set<Cita> getEmpleadoCitas() {
        return empleadoCitas;
    }

    public void setEmpleadoCitas(final Set<Cita> empleadoCitas) {
        this.empleadoCitas = empleadoCitas;
    }

    public Set<ConsumoMedicamento> getPacienteConsumoMedicamentoes() {
        return pacienteConsumoMedicamentoes;
    }

    public void setPacienteConsumoMedicamentoes(
            final Set<ConsumoMedicamento> pacienteConsumoMedicamentoes) {
        this.pacienteConsumoMedicamentoes = pacienteConsumoMedicamentoes;
    }

    public OffsetDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(final OffsetDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public OffsetDateTime getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(final OffsetDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

}
