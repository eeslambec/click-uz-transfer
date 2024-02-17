package uz.pdp.clickuztransactionsservice.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.clickuztransactionsservice.dto.Response;
import uz.pdp.clickuztransactionsservice.exception.InvalidArgumentException;
import uz.pdp.clickuztransactionsservice.exception.NotFoundException;
import uz.pdp.clickuztransactionsservice.exception.NullOrEmptyException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e){
        return ResponseEntity.ok(new Response(e.getMessage()));
    }
    @ExceptionHandler(InvalidArgumentException.class)
    public ResponseEntity<?> handleInvalidArgumentException(InvalidArgumentException e){
        return ResponseEntity.ok(new Response(e.getMessage()));
    }
    @ExceptionHandler(NullOrEmptyException.class)
    public ResponseEntity<?> handleNullOrEmptyException(NullOrEmptyException e){
        return ResponseEntity.ok(new Response(e.getMessage()));
    }
}
