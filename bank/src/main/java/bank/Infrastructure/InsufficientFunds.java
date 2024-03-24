package bank.Infrastructure;

public class InsufficientFunds extends RuntimeException {
    public InsufficientFunds(String message, int id) {
        super(message + id);
    }
}