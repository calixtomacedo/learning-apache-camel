package br.com.cmdev.atom.users.registration.exception;

import br.com.cmdev.atom.users.registration.domain.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
public class UsersRegistrationExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest request) {
        var message = this.getFieldErrorsMessages(exception);
        var response = this.getMessageErrorDetails(HttpStatus.UNPROCESSABLE_ENTITY, message, request);
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(response);
    }

    private ErrorResponse getMessageErrorDetails(HttpStatus httpStatus, String message, HttpServletRequest request) {
       return new ErrorResponse(LocalDateTime.now(), httpStatus.value(), message, request.getMethod().concat(":").concat(request.getRequestURI()));
    }

    private String getFieldErrorsMessages(MethodArgumentNotValidException exception) {
        try {
            return exception.getBindingResult().getFieldErrors().stream().map(error -> error.getField() + ": " + error.getDefaultMessage()).collect(Collectors.joining(" | "));
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

}
