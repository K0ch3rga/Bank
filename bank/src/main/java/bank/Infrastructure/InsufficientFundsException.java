package bank.Infrastructure;

public class InsufficientFundsException extends RuntimeException {
    public InsufficientFundsException(String message, long id) {
        super(message + id);
    }
}