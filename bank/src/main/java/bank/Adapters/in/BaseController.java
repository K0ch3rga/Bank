package bank.Adapters.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bank.Application.usecases.AccountUsecase;

@Controller
public class BaseController {
    AccountUsecase accountUsecase;

    @Autowired
    public BaseController(AccountUsecase accountUsecase) {
        this.accountUsecase = accountUsecase;
    }

    @GetMapping("/status")
    @ResponseBody
    public String ok() {
        return "OK";
    }

    @GetMapping("/check/{id}")
    @ResponseBody
    public String checkBalance(long id) {
        var acc = accountUsecase.getAccountById(id);
        if (acc.isPresent()) {
            return "Balance: " + String.valueOf(acc.get().balance());
        } else {
            return "No account";
        }
    }
}
