package uz.pdp.clickuztransactionsservice.exception;

public class NotEnoughMoneyException extends RuntimeException {
    public NotEnoughMoneyException() {
        super("Money is not enough!");
    }
}
