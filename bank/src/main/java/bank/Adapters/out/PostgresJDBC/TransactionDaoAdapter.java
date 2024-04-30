package bank.Adapters.out.PostgresJDBC;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bank.Adapters.out.PostgresJDBC.entities.TransactionEntity;
import bank.Adapters.out.PostgresJDBC.repositories.TransactionRepository;
import bank.Application.dao.TransactionDao;
import bank.Application.dto.NewTransactionDto;
import bank.Domain.Transaction;

@Service
public class TransactionDaoAdapter implements TransactionDao {
    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public synchronized void createTransaction(NewTransactionDto transaction) {
        transactionRepository.save(new TransactionEntity());
    }

    @Override
    public synchronized List<Transaction> getAllbyClientId(long id) {
        return transactionRepository.findByBankAccountIdOrRecipientBankAccountId(id, id).stream()
                .map((t) -> new Transaction(t.id, t.amount, t.currency, t.time, t.transactionType, t.bankAccountId,
                        t.recipientBankAccountId))
                .toList();
    }
}
