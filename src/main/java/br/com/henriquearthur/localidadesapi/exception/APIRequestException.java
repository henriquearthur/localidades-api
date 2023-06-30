package br.com.henriquearthur.localidadesapi.exception;

import org.springframework.http.HttpStatus;

public class APIRequestException extends Exception {
    private final HttpStatus httpStatus;
    private final String message;

    public APIRequestException(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
    }
}
