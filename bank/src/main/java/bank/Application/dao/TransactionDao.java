package bank.Application.dao;

import java.util.List;

import bank.Application.dto.NewTransactionDto;
import bank.Domain.Transaction;

public interface TransactionDao {
    void createTransaction(NewTransactionDto transaction);
    List<Transaction> getAllbyClientId(long id);
}
