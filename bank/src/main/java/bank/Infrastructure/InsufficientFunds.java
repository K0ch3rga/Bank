package bank.Infrastructure;

public class InsufficientFunds extends RuntimeException {
    public InsufficientFunds(String message, long id) {
        super(message + id);
    }
}