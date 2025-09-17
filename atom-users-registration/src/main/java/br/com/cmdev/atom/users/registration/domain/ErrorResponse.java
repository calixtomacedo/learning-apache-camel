package br.com.cmdev.atom.users.registration.domain;

import java.time.LocalDateTime;

public record ErrorResponse(LocalDateTime timestamp, Integer statusCode, String message, String path) { }
