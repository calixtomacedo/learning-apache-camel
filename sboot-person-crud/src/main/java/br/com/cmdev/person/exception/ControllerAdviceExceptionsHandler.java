package br.com.cmdev.person.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class ControllerAdviceExceptionsHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseErrorMessage> handler(RuntimeException exception) {
        var classError = Arrays.stream(exception.getStackTrace()).findFirst();
        StringBuilder builderMessage = new StringBuilder();
        builderMessage.append(exception.getClass().getCanonicalName());
        builderMessage.append(" occurred in the class: ".concat(classError.get().getClassName()));
        builderMessage.append(" Method: ".concat(classError.get().getMethodName()));
        builderMessage.append(" Number line: ".concat(String.valueOf(classError.get().getLineNumber())));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR,  exception.getMessage(), builderMessage.toString(), exception.getCause().getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<ResponseErrorFields>> handler(MethodArgumentNotValidException validException) {
        List<FieldError> fieldErrors = validException.getBindingResult().getFieldErrors();
        List<ResponseErrorFields> responseErrorsList = new ArrayList<>();
        fieldErrors.forEach(error -> {
            responseErrorsList.add(new ResponseErrorFields(error.getField(), error.getDefaultMessage()));
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseErrorsList);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity handler(HttpRequestMethodNotSupportedException exception) {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(exception.getBody());
    }

}
