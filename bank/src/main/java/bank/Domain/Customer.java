package bank.Domain;

public record Customer(
        long id,
        String firstName,
        String lastName,
        String phone,
        String email
) {
}