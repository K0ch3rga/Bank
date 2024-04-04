package bank.Adapters.out.PostgresJDBC.repositories;

import org.springframework.data.repository.CrudRepository;
import bank.Adapters.out.PostgresJDBC.entities.AccountEntity;

public interface AccountRepository extends CrudRepository<AccountEntity, Long> {

} 
