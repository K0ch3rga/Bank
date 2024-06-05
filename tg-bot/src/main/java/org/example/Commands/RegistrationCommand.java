package org.example.Commands;

import bank.Adapters.out.PostgresJDBC.CustomerDaoAdapter;
import bank.Adapters.out.PostgresJDBC.entities.TgCustomerEntity;
import bank.Adapters.out.PostgresJDBC.repositories.TgCustomerRepository;
import bank.Application.dto.NewCustomerDto;
import bank.Domain.Roles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class RegistrationCommand extends Command {
    @Autowired
    private CustomerDaoAdapter customerDaoAdapter;
    @Autowired
    private TgCustomerRepository tgCustomerRepository;

    public RegistrationCommand() {
        super(
                "/registration",
                "Зарегистрироваться",
                "/registration <имя> <фамилия> <телефон> <email> <пароль>",
                new Roles[]{Roles.NOTAUTHORIZED});

    }

    @Override
    public String execute(String[] args, Roles role, long chatId) {
        var roles = new HashSet<Roles>() {
        };
        roles.add(Roles.CUSTOMER);
        var customer = new NewCustomerDto(args[0], args[1], args[2], args[3], args[4]);
        customerDaoAdapter.saveCustomer(customer);
        var customerEntity = customerDaoAdapter.loadCustomerByEmail(customer.email());
        var tgCustomer = new TgCustomerEntity(customerEntity.get().getId(), chatId);
        tgCustomerRepository.save(tgCustomer);
        return "Вы успешно зарегистрированы ";
    }


}
