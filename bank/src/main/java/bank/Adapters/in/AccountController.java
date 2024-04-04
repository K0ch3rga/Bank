package bank.Adapters.in;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import bank.Application.dto.NewAccountDto;
import bank.Application.usecases.AccountUsecase;
import bank.Domain.BankAccount;

@Controller
@ResponseBody
public class AccountController {
    @Autowired
    private AccountUsecase accountUsecase;

    @GetMapping("")
    public String ok() {
        return "OK";
    }

    @GetMapping("/create")
    public String create() {
        return accountUsecase.createNewAccount(new NewAccountDto(0, 0, null)).toString();
    }

    @GetMapping("/list")
    public List<BankAccount> list() {
        return accountUsecase.getAll();
    }

    @GetMapping("/{id}")
    public BankAccount get(@PathVariable long id) {
        return accountUsecase.getAccountById(id).get();
    }

}
