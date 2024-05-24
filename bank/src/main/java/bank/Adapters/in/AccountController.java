package bank.Adapters.in;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import bank.Application.dto.NewAccountDto;
import bank.Application.usecases.AccountUsecase;
import bank.Application.usecases.CustomerUsecase;
import bank.Domain.BankAccount;

@Controller
public class AccountController {
    private final AccountUsecase accountUsecase;
    private final CustomerUsecase customerUsecase;
    @Autowired
    public AccountController(AccountUsecase accountUsecase, CustomerUsecase customerUsecase) {
        this.accountUsecase = accountUsecase;
        this.customerUsecase = customerUsecase;
    }

    @GetMapping("/list")
    public List<BankAccount> list() {
        return accountUsecase.getAll();
    }

    @GetMapping("/{id}")
    public BankAccount get(@PathVariable long id) { // REMOVE
        return accountUsecase.getAccountById(id).get();
    }

    @GetMapping("/newbill")
    public String newbill(Model model) {
        return "newbill";
    }

    @GetMapping("/style")
    public String style() {
        return "main";
    }

    @PostMapping("/newbill")
    public String newbillPost(NewAccountDto newAccount,Model model) {
        var customer = customerUsecase.getCustomer();
        BankAccount account = accountUsecase.createNewAccount(new NewAccountDto(customer.id(), 0, newAccount.currency()));
        model.addAttribute("account", account);
        return "redirect:/billadded";
    }

    // FIXME Лучше перенести это в модальное окно/попап/<dialog> метода выше
    @GetMapping("/billadded")
    public String billadded(Model model) {
        return "billadded";
    }

    @GetMapping("/checkbalance")
    public String checkbalance(Model model) {
        return "checkbalance";
    }

    @GetMapping("/showbalance")
    public String showbalance(Model model) {
        var account = accountUsecase.getAccountById(0); // FIXME где брать id?
        if (account.isPresent()) {
            model.addAttribute("account", account); // FIXME И как взаимодействовать с optional
        }
        return "showbalance";
    }

    @GetMapping("/printbalance")
    public String printbalance(Model model) {
        return "printbalance";
    }
}
