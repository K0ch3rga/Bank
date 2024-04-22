package bank.Application.dto;

import java.time.LocalDateTime;

import bank.Domain.CurrencyType;
import bank.Domain.TransactionType;

public record NewTransactionDto(long amount,
        CurrencyType currency,
        LocalDateTime time,
        TransactionType transactionType,
        long bankAccountId,
        long RecipientBankAccountId) {
}