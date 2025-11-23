package br.com.cmdev.demo.apache.camel.spring.error;

public class DemoCamelException extends RuntimeException {

    public DemoCamelException(String message) {
        super(message);
    }

    public DemoCamelException(String message, Throwable cause) {
        super(message, cause);
    }

}
