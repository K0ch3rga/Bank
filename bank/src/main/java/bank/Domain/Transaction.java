package bank.Domain;

import java.time.LocalDateTime;

public record Transaction(
        int id,
        int amount,
        CurrencyType currency,
        LocalDateTime time,
        TransactionType transactionType,
        int bankAccountId,
        int RecipientBankAccountId
) {
}
