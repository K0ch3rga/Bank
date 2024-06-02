package bank.Application.dao;

import java.util.List;

import bank.Domain.NewTransactionDto;
import bank.Domain.Transaction;

public interface TransactionDao {
    void createTransaction(NewTransactionDto transaction);

    List<Transaction> getAllbyAccountId(long id);
}
