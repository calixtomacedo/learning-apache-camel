package br.com.cmdev.person.exceptions;

import org.springframework.http.HttpStatus;

public record ResponseErrorMessage(HttpStatus status, String exception, String message) {
}
