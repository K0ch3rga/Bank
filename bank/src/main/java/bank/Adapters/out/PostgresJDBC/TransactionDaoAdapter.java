package bank.Adapters.out.PostgresJDBC;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.Adapters.out.PostgresJDBC.entities.TransactionEntity;
import bank.Adapters.out.PostgresJDBC.repositories.TransactionRepository;
import bank.Application.dao.TransactionDao;
import bank.Domain.NewTransactionDto;
import bank.Domain.Transaction;

@Service
public class TransactionDaoAdapter implements TransactionDao {
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public synchronized void createTransaction(NewTransactionDto transaction) {
        transactionRepository.save(new TransactionEntity(0, transaction.amount(), transaction.currency(),
                transaction.time(), transaction.transactionType(), transaction.bankAccountId(),
                transaction.RecipientBankAccountId()));
    }

    @Override
    public synchronized List<Transaction> getAllbyAccountId(long id) {
        return transactionRepository.findByBankAccountIdOrRecipientBankAccountId(id, id).stream()
                .map((t) -> new Transaction(t.getId(), t.getAmount(), t.getCurrency(), t.getTime(), t.getTransactionType(), t.getBankAccountId(),
                        t.getRecipientBankAccountId()))
                .toList();
    }
}
