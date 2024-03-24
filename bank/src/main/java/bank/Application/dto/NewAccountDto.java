package bank.Application.dto;

import bank.Domain.CurrencyType;

public record NewAccountDto(
        int balance,
        int accountHolder,
        int bankId,
        CurrencyType currency
) {
}