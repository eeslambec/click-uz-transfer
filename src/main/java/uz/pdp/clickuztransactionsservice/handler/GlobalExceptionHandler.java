package uz.pdp.clickuztransactionsservice.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.pdp.clickuztransactionsservice.dto.CustomResponseEntity;
import uz.pdp.clickuztransactionsservice.exception.BadRequestException;
import uz.pdp.clickuztransactionsservice.exception.InvalidArgumentException;
import uz.pdp.clickuztransactionsservice.exception.NotFoundException;
import uz.pdp.clickuztransactionsservice.exception.NullOrEmptyException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public CustomResponseEntity<?> handleNotFoundException(NotFoundException e){
        return CustomResponseEntity.badRequest(e.getMessage());
    }
    @ExceptionHandler(InvalidArgumentException.class)
    public CustomResponseEntity<?> handleInvalidArgumentException(InvalidArgumentException e){
        return CustomResponseEntity.badRequest(e.getMessage());
    }
    @ExceptionHandler(NullOrEmptyException.class)
    public CustomResponseEntity<?> handleNullOrEmptyException(NullOrEmptyException e){
        return CustomResponseEntity.badRequest(e.getMessage());
    }
    @ExceptionHandler(BadRequestException.class)
    public CustomResponseEntity<?> handleBadRequestException(BadRequestException e){
        return CustomResponseEntity.badRequest(e.getMessage());
    }
}
