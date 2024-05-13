package bank.Adapters.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import bank.Application.dto.NewTransactionDto;
import bank.Application.usecases.CustomerUsecase;
import bank.Application.usecases.TransferUsecase;
import bank.Domain.Customer;
import bank.Infrastructure.AccountDoesntExistExeption;
import bank.Infrastructure.InsufficientFundsException;

@Controller
public class TransferController {
    @Autowired
    TransferUsecase transferUsecase;
    @Autowired
    CustomerUsecase customerUsecase;

    @GetMapping("/newtransfer")
    public String newtransfer(Model model) {
        return "newtransfer";
    }

    @PostMapping("/newtransfer")
    public String newtransferPost(NewTransactionDto transaction, Model model) {
        Customer customer = customerUsecase.getCustomer(); // FIXME Придумать способ, как иначе получать
        try {
            transferUsecase.transferMoney(customer.id(), transaction.RecipientBankAccountId(), transaction.amount());
        } catch (InsufficientFundsException e) {
            return "redirect:/transferErrorBalance";
        } catch (AccountDoesntExistExeption e) {
            return "redirect:/transferErrorNums";
        }

        return "transferOk";
    }

    // Нужно прописать три варианта - если баланс ниже суммы перевода, то страница "transferErrorBalance.html",
    // если реквизиты неверны, то страница "transferErrorNums.html"
    // в случае успеха снимаются деньги и производится переход на страницу "transferOk.html"

    @GetMapping("/transferOk")
    public String transferOk(Model model) {
        return "transferOk";
    }

    @GetMapping("/transferErrorBalance")
    public String transferErrorBalance(Model model) {
        return "transferErrorBalance";
    }

    @GetMapping("/transferErrorNums")
    public String transferErrorNums(Model model) {
        return "transferErrorNums";
    }

    @GetMapping("/withdraft")
    public String withdraft(Model model) {
        return "withdraft";
    }

    @PostMapping("/withdraft")
    public String withdraftPost(NewTransactionDto transactionDto, Model model) {
        Customer customer = customerUsecase.getCustomer();
        try {
            transferUsecase.withdrawMoney(customer.id(), transactionDto.amount());
        } catch (InsufficientFundsException e) {
            return "redirect:/withdraftError";
        }
        return "withdraftOk";
    }

    // Нужно прописать два варианта - если баланс ниже суммы перевода, то страница "withdraftError.html",
    // в случае успеха снимаются деньги и производится переход на страницу "withdraftOk.html"

    @GetMapping("/withdraftError")
    public String withdraftError(Model model) {
        return "withdraftError";
    }

    @GetMapping("/withdraftOk")
    public String withdraftOk(Model model) {
        return "withdraftOk";
    }

    @GetMapping("/newdeposit")
    public String newdeposit(Model model) {
        return "newdeposit";
    }

    @PostMapping("/newdeposit")
    public String newdepositPost(NewTransactionDto transactionDto, Model model) {
        Customer customer = customerUsecase.getCustomer();
        transferUsecase.refillMoney(customer.id(), transactionDto.amount());
        return "depositOk";
    }

    @GetMapping("/styledpage")
    public String styledpage(Model model) {
        return "styledpage";
    }

    @GetMapping("/depositOk")
    public String depositOk(Model model) {
        return "depositOk";
    }
}
