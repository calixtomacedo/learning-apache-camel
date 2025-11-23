package br.com.cmdev.demo.apache.camel.spring.error;

public record ResponseError(Integer code, String message, Object data) {}
