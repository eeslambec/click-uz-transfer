package uz.pdp.clickuztransactionsservice.exception;

public class InvalidArgumentException extends RuntimeException{
    public InvalidArgumentException(String m){
        super("Invalid " + m);
    }
}
