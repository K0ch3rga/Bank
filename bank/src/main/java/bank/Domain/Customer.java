package bank.Domain;

import java.util.Set;

public record Customer(
        long id,
        String firstName,
        String lastName,
        String phone,
        String email,
        String password,
        Set<Roles> roles
) {
}