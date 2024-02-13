package uz.pdp.clickuztransactionsservice.exception;

public class MoneyLessThanMinimumException extends RuntimeException{
    public MoneyLessThanMinimumException(String message) {
        super("Money is less than minimum!");
    }
}
