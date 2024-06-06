package bank.Adapters.out.PostgresJDBC.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bank.Adapters.out.PostgresJDBC.entities.TransactionEntity;

@Repository
public interface TransactionRepository extends CrudRepository<TransactionEntity, Long> {
    public List<TransactionEntity> findByBankAccountIdOrRecipientBankAccountId(long id, long rId);
}
