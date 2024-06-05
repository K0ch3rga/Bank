package bank.Adapters.out.PostgresJDBC.repositories;

import bank.Adapters.out.PostgresJDBC.entities.CustomerEntity;
import bank.Adapters.out.PostgresJDBC.entities.TgCustomerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TgCustomerRepository extends CrudRepository<TgCustomerEntity, Long> {

}
