package org.weekly.weekly.web.exception;

public class WebExceptionDto {
    private String message;
    public WebExceptionDto(RuntimeException error) {
        this.message = error.getMessage();
    }

    public String getMessage() {
        return message;
    }
}