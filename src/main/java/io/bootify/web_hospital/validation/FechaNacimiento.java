package io.bootify.web_hospital.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Anotación para validar que la fecha de nacimiento proporcionada en un campo sea
 * anterior o igual a la fecha actual. Esta restricción asegura que las fechas de nacimiento
 * ingresadas sean lógicamente válidas y no futuras.
 *
 * Puede ser aplicada a campos de entidades o DTOs que representen fechas de nacimiento.
 */
@Documented
@Constraint(validatedBy = FechaNacimientoValidator.class)
@Target({FIELD})
@Retention(RUNTIME)
public @interface FechaNacimiento {

    /**
     * Mensaje de error predeterminado que se devuelve cuando la validación falla.
     *
     * @return El mensaje de error.
     */
    String message() default "La fecha de nacimiento no puede ser posterior a la fecha actual";

    /**
     * Permite especificar grupos de validación para condicionar la ejecución de la validación.
     *
     * @return Grupos de validación a los que pertenece esta restricción.
     */
    Class<?>[] groups() default {};

    /**
     * Puede ser utilizado por clientes de la API de validación para asignar
     * un payload personalizado a una restricción.
     *
     * @return Clases que extienden {@link Payload} para proporcionar información adicional
     * sobre el comportamiento de la restricción.
     */
    Class<? extends Payload>[] payload() default {};
}
