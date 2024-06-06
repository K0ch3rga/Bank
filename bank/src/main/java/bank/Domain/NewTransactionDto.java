package bank.Domain;

import java.time.LocalDateTime;

public record NewTransactionDto(
        long amount,
        CurrencyType currency,
        LocalDateTime time,
        TransactionType transactionType,
        long bankAccountId,
        long RecipientBankAccountId) {

}
