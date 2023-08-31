package br.com.henriquearthur.localidadesapi.config;

import br.com.henriquearthur.localidadesapi.exception.APIRequestError;
import br.com.henriquearthur.localidadesapi.exception.APIRequestException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class DefaultControllerAdvice {
    @ExceptionHandler(APIRequestException.class)
    @ResponseBody
    ResponseEntity<APIRequestError> apiRequestErrorListener(APIRequestException exception, HttpServletRequest request) {
        APIRequestError error = new APIRequestError(exception.getHttpStatus().value(), request.getRequestURI(), exception.getMessage());
        return new ResponseEntity<>(error, exception.getHttpStatus());
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    ResponseEntity<APIRequestError> missingServletRequestParameterExceptionListener(MissingServletRequestParameterException exception, HttpServletRequest request) {
        APIRequestError error = new APIRequestError(HttpStatus.BAD_REQUEST.value(), request.getRequestURI(), "Parâmetro '" + exception.getParameterName() + "' do tipo " + exception.getParameterType() + " não informado");
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    ResponseEntity<APIRequestError> exceptionListener(Exception exception, HttpServletRequest request) {
        APIRequestError error = new APIRequestError(HttpStatus.INTERNAL_SERVER_ERROR.value(), request.getRequestURI(), "Ocorreu um erro inesperado - " + exception.getMessage());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
