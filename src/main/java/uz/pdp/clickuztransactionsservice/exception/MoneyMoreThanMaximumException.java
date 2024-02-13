package uz.pdp.clickuztransactionsservice.exception;

public class MoneyMoreThanMaximumException extends RuntimeException{
    public MoneyMoreThanMaximumException() {
        super("Money is more than maximum!");
    }
}
