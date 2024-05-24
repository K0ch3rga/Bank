package bank.Adapters.out.PostgresJDBC.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bank.Adapters.out.PostgresJDBC.entities.CustomerEntity;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findByFirstName(String firstName);
    Optional<CustomerEntity> findByEmail(String email);
}
