package com.nwnx.rs.dto.responses;

import com.nwnx.components.SystemProperties;

import javax.validation.ConstraintViolation;
import java.util.Objects;

public class ViolationResponse {

    private final String property;
    private final String message;
    private final String invalidValue;

    private ViolationResponse(String property, String message, String invalidValue) {
        this.property = Objects.requireNonNull(property);
        this.message = Objects.requireNonNull(message);
        this.invalidValue = invalidValue;
    }

    private ViolationResponse(String property, String message) {
        this(property, message, null);
    }

    public static ViolationResponse from(ConstraintViolation cv) {
        Objects.requireNonNull(cv);

        String property = cv.getPropertyPath().toString();
        if (!SystemProperties.isDevMode()) {
            return new ViolationResponse(property, cv.getMessage());
        }

        return new ViolationResponse(property,
                                    cv.getMessage(),
                                    cv.getInvalidValue() == null ? null : cv.getInvalidValue().toString());
    }

    public String getInvalidValue() {
        return invalidValue;
    }

    public String getProperty() {
        return property;
    }

    public String getMessage() {
        return message;
    }
}
