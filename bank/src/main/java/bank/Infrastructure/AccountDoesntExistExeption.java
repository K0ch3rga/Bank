package bank.Infrastructure;

public class AccountDoesntExistExeption extends RuntimeException {
    public AccountDoesntExistExeption(String message, int id) {
        super("Cannot find acount with id "+ id + message) ;
    }    
}