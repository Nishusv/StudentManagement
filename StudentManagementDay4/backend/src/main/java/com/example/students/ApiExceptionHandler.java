package com.example.students;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestControllerAdvice
public class ApiExceptionHandler {
 @ExceptionHandler(StudentNotFoundException.class) ResponseEntity<?> notFound(RuntimeException e){return ResponseEntity.status(404).body(Map.of("message",e.getMessage()));}
 @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<?> badRequest(RuntimeException e){return ResponseEntity.badRequest().body(Map.of("message",e.getMessage()));}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<?> invalid(MethodArgumentNotValidException e){String msg=e.getBindingResult().getFieldErrors().stream().findFirst().map(x->x.getField()+": "+x.getDefaultMessage()).orElse("Invalid input");return ResponseEntity.badRequest().body(Map.of("message",msg));}
}
