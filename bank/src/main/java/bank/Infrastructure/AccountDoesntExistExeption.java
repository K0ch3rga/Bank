package bank.Infrastructure;

public class AccountDoesntExistExeption extends RuntimeException {
    public AccountDoesntExistExeption(String message, long id) {
        super("Cannot find acount with id "+ id + message) ;
    }    
}