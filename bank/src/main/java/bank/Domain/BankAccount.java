package bank.Domain;

public record BankAccount(
        long id,
        long balance,
        long accountHolder,
        long bankId,
        CurrencyType currency
) {
}
