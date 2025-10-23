package sk.ukf.demo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sk.ukf.demo.response.ResponseWrapper;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseWrapper<String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        String errors = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));
        return ResponseEntity.badRequest().body(ResponseWrapper.<String>builder()
                .message("Validation error: " + errors)
                .build());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseWrapper<String>> handleRuntimeException(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ResponseWrapper.<String>builder()
                .message("Error: " + ex.getMessage())
                .build());
    }
}