package bank.Domain;

import java.time.LocalDateTime;

public record Transaction(
        long id,
        long amount,
        CurrencyType currency,
        LocalDateTime time,
        TransactionType transactionType,
        long bankAccountId,
        long recipientBankAccountId
) {
}
