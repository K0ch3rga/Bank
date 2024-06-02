package bank.Adapters.in;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import bank.Application.dto.NewAccountDto;
import bank.Application.dto.NewAccountCurrencyWrapper;
import bank.Application.usecases.AccountUsecase;
import bank.Application.usecases.CustomerUsecase;
import bank.Application.usecases.TransferUsecase;
import bank.Domain.BankAccount;

@Controller
public class AccountController {
    private final AccountUsecase accountUsecase;
    private final CustomerUsecase customerUsecase;
    private final TransferUsecase transferUsecase;

    @Autowired
    public AccountController(AccountUsecase accountUsecase, CustomerUsecase customerUsecase,
            TransferUsecase transferUsecase) {
        this.accountUsecase = accountUsecase;
        this.customerUsecase = customerUsecase;
        this.transferUsecase = transferUsecase;
    }

    @GetMapping("/newbill")
    public String newbill(Model model) {
        return "newbill";
    }

    @GetMapping("/style")
    public String style() {
        return "main";
    }

    @GetMapping("/mybills")
    public String mybills(Model model) {
        var customer = customerUsecase.getCustomer();
        var accounts = accountUsecase.getAllByCustomerId(customer.id());
        model.addAttribute("accounts", accounts);
        return "mybills";
    }

    @PostMapping("/newbill")
    public String newbillPost(NewAccountCurrencyWrapper newAccountCurrencyType, Model model) {
        var customer = customerUsecase.getCustomer();
        BankAccount account = accountUsecase
                .createNewAccount(new NewAccountDto(customer.id(), 0, newAccountCurrencyType.currency()));
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
    public String showbalance(@RequestParam("id") long id, Model model) {
        var customer = customerUsecase.getCustomer();
        var account = accountUsecase.getAccountById(id);
        var transactions = transferUsecase.geTransactions(id);
        if (account.isPresent() && account.get().accountHolder() == customer.id()) {
            model.addAttribute("account", account.get());
            model.addAttribute("transactions", transactions);
        } else {
            // FIXME errors
        }
        return "showbalance";
    }

    @GetMapping("/printbalance")
    public String printbalance(Model model) {
        return "printbalance";
    }
}
