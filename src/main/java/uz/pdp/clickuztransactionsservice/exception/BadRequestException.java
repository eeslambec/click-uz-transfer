package uz.pdp.clickuztransactionsservice.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String m){
        super(m);
    }
}
