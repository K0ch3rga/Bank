package bank.Adapters.out.PostgresJDBC.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import bank.Adapters.out.PostgresJDBC.entities.CustomerEntity;

import java.util.Optional;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity, Long> {
    CustomerEntity findByFirstName(String firstName);
    CustomerEntity findByEmail(String email);
    CustomerEntity findFirtById(Long id);
}
