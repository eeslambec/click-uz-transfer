package uz.pdp.clickuztransactionsservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class CustomResponseEntity<T>{
    private String errorMessage;
    private T body;
    private HttpStatus statusCode;
    public CustomResponseEntity(String errorMessage,HttpStatus statusCode) {
        this.errorMessage = errorMessage;
        this.statusCode = statusCode;
    }
    public CustomResponseEntity(T body, HttpStatus status){
        this.body = body;
        this.statusCode = status;
    }
    public static <T> CustomResponseEntity<T> ok(T t){
        return new CustomResponseEntity<>(t,HttpStatus.OK);
    }
    public static <T> CustomResponseEntity<T> badRequest(String errorMessage){
        return new CustomResponseEntity<>(errorMessage,HttpStatus.BAD_REQUEST);
    }
}
