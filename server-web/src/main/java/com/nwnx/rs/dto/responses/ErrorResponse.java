package com.nwnx.rs.dto.responses;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.google.common.base.Throwables;
import com.nwnx.components.SystemProperties;

import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private final int code;
    private final String message;
    private final String stackTrace;

    private ErrorResponse(int code, String message, String stackTrace) {
        this.code = code;
        this.message = message;
        this.stackTrace = stackTrace;
    }

    public static ErrorResponse of(int code, String message, Throwable throwable) {
        Objects.requireNonNull(message);
        return new ErrorResponse(code, message, SystemProperties.isDevMode() ? Throwables.getStackTraceAsString(throwable) : null);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getStackTrace() {
        return stackTrace;
    }
}
