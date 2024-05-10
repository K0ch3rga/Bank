package bank.Adapters.out.PostgresJDBC.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import bank.Adapters.out.PostgresJDBC.entities.AccountEntity;

@Repository
public interface AccountRepository extends CrudRepository<AccountEntity, Long> {
    public List<AccountEntity> findAllByAccountHolder(long accountHolder);
} 
