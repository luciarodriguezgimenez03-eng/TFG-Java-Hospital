package io.bootify.web_hospital.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * Anotación para validar que la fecha de una cita sea igual o posterior a la fecha actual.
 * Esta validación asegura que no se puedan programar citas en el pasado.
 *
 * Puede ser aplicada a campos de tipo fecha en entidades o modelos de datos que representan citas
 * dentro de la aplicación del Hospital Mendoza.
 */
@Documented
@Constraint(validatedBy = FechaCitaValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface FechaCitaValida {

    /**
     * Mensaje de error predeterminado que se devuelve cuando la validación falla.
     *
     * @return El mensaje de error.
     */
    String message() default "La fecha de la cita debe ser igual o posterior a la fecha actual";

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
