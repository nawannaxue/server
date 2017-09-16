package com.nwnx.rs.dto.responses;

import javax.validation.ConstraintViolation;
import java.util.Objects;

public class ViolationResponse {

    private final String invalidValue;

    private final String propertyPath;

    private final String message;

    private ViolationResponse(String invalidValue, String propertyPath, String message) {
        this.invalidValue = invalidValue;
        this.propertyPath = Objects.requireNonNull(propertyPath);
        this.message = Objects.requireNonNull(message);
    }

    public static ViolationResponse from(ConstraintViolation cv) {
        Objects.requireNonNull(cv);
        return new ViolationResponse(cv.getInvalidValue() == null ? null : cv.getInvalidValue().toString(),
                                        cv.getPropertyPath().toString(),
                                        cv.getMessage());
    }

    public String getInvalidValue() {
        return invalidValue;
    }

    public String getPropertyPath() {
        return propertyPath;
    }

    public String getMessage() {
        return message;
    }
}
