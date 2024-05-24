package bank.Application.dto;

public record NewTransferDto(
        long amount,
        long bankAccountId,
        long RecipientBankAccountId) {
}