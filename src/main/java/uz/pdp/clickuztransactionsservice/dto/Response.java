package uz.pdp.clickuztransactionsservice.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Response {

    private final String message;
    private final LocalDateTime timestamp = LocalDateTime.now();
    public Response(String message){
        this.message = message;
    }
}
