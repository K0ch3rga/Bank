package bank.Application.dto;

public record NewCustomerDto(
        String firstName,
        String lastName,
        String phone,
        String email,
        String password) {
}