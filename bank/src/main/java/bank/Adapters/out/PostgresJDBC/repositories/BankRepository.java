package bank.Adapters.out.PostgresJDBC.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bank.Adapters.out.PostgresJDBC.entities.BankEntity;


@Repository
public interface BankRepository extends CrudRepository<BankEntity, Long> {
    
}
