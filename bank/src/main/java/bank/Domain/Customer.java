package bank.Domain;

public record Customer(
        int id,
        String firstName,
        String lastName,
        String phone,
        String email
) {
}