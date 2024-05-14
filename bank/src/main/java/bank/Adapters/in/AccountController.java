package bank.Adapters.in;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bank.Application.dto.NewAccountDto;
import bank.Application.usecases.AccountUsecase;
import bank.Domain.BankAccount;

@Controller
public class AccountController {
    @Autowired
    private AccountUsecase accountUsecase;

    @GetMapping("/list")
    public List<BankAccount> list() {
        return accountUsecase.getAll();
    }

    @GetMapping("/{id}")
    public BankAccount get(@PathVariable long id) {
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
    public String newbillPost(Model model) {
        BankAccount account = accountUsecase.createNewAccount(new NewAccountDto(0, 0, null));
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

    @GetMapping("/login1")
    public String login1(Model model) {
        return "login1";
    }

    @GetMapping("/printbalance")
    public String printbalance(Model model) {
        return "printbalance";
    }
}
