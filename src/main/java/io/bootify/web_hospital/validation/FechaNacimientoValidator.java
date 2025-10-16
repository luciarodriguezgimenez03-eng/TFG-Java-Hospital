package io.bootify.web_hospital.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

/**
 * Implementa la validación personalizada para asegurar que la fecha de nacimiento
 * no sea posterior a la fecha actual. Esta clase es el componente de validación
 * que acompaña a la anotación {@link FechaNacimiento}.
 *
 * La validación es aplicable a campos de tipo {@link LocalDate}.
 */
public class FechaNacimientoValidator implements ConstraintValidator<FechaNacimiento, LocalDate> {

    /**
     * Verifica si el valor proporcionado para la fecha de nacimiento cumple con la condición
     * de no ser posterior a la fecha actual.
     *
     * @param value El valor de la fecha de nacimiento que se está validando.
     * @param context Contexto en el cual la validación es ejecutada. No se utiliza en esta implementación.
     * @return {@code true} si la fecha de nacimiento no es posterior a la fecha actual, o si el valor es {@code null}.
     *         Se asume que la validación para {@code null} se maneja por separado con anotaciones como {@link jakarta.validation.constraints.NotNull}.
     */
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // La validación de nulidad se maneja con @NotNull
        }
        return !value.isAfter(LocalDate.now());
    }
}
