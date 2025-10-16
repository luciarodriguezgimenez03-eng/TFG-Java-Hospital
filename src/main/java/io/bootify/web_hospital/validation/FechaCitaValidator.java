package io.bootify.web_hospital.validation;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

/**
 * Implementa la validación personalizada para asegurar que la fecha de una cita
 * sea igual o posterior a la fecha actual. Esta clase es el componente de validación
 * que acompaña a la anotación {@link FechaCitaValida}.
 *
 * La validación es aplicable a campos de tipo {@link LocalDate}.
 */
public class FechaCitaValidator implements ConstraintValidator<FechaCitaValida, LocalDate> {


    /**
     * Verifica si el valor proporcionado para la fecha de la cita cumple con la condición
     * de ser igual o posterior a la fecha actual.
     *
     * @param value El valor de la fecha de la cita que se está validando.
     * @param context Contexto en el cual la validación es ejecutada. No se utiliza en esta implementación.
     * @return {@code true} si la fecha de la cita es igual o posterior a la fecha actual, o si el valor es {@code null}.
     *         Se asume que la validación para {@code null} se maneja por separado con anotaciones como {@link jakarta.validation.constraints.NotNull}.
     */
    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Se asume que la validación @NotNull se maneja por separado
        }
        return !value.isBefore(LocalDate.now());
    }
}
