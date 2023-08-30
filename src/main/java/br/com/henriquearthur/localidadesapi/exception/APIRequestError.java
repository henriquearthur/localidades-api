package br.com.henriquearthur.localidadesapi.exception;

public record APIRequestError(Integer status, String path, String message) {
}
