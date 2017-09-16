package com.nwnx.rs.dto.responses;

public class ErrorResponse {

    private final int code;

    private final String message;

    private final String exception;

    public ErrorResponse(int code, String message, String exception) {
        this.code = code;
        this.message = message;
        this.exception = exception;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getException() {
        return exception;
    }
}
