package com.sdbawa.movies.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

/**
 * @author simar bawa
 */
public class ControllerUtils {

    private ControllerUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getValidationErrors(BindingResult result) {
        StringBuilder fieldErrors = new StringBuilder();
        List<FieldError> errors = result.getFieldErrors();
        for (FieldError err : errors) {
            fieldErrors.append(err.getDefaultMessage());
            fieldErrors.append(",");
        }
        if (fieldErrors.length() > 0) {
            fieldErrors.deleteCharAt(fieldErrors.length() - 1 );
        }
        return fieldErrors.toString();
    }
}
