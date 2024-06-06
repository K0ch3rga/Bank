package bank.Application.dto;

import bank.Domain.CurrencyType;

public record NewAccountDto(
        long accountHolder,
        long bankId,
        CurrencyType currency
) {
}