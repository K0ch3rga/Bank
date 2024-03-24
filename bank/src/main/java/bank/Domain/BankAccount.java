package bank.Domain;

public record BankAccount(
        int id,
        float balance,
        int accountHolder,
        int bankId,
        CurrencyType currency
) {
}
