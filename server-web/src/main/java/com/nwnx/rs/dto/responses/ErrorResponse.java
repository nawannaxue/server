package com.nwnx.rs.dto.responses;

import com.google.common.base.Throwables;

import java.util.Objects;

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
        return new ErrorResponse(code, message, Throwables.getStackTraceAsString(throwable));
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
