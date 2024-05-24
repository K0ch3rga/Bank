package bank.Adapters.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import bank.Application.dto.NewRefillDto;
import bank.Application.dto.NewTransferDto;
import bank.Application.dto.NewWithdrawalDto;
import bank.Application.usecases.AccountUsecase;
import bank.Application.usecases.CustomerUsecase;
import bank.Application.usecases.TransferUsecase;
import bank.Domain.Customer;
import bank.Infrastructure.AccountDoesntExistExeption;
import bank.Infrastructure.InsufficientFundsException;

@Controller
public class TransferController {
    private final TransferUsecase transferUsecase;
    private final CustomerUsecase customerUsecase;
    private final AccountUsecase accountUsecase;

    @Autowired
    public TransferController(TransferUsecase transferUsecase, CustomerUsecase customerUsecase,
            AccountUsecase accountUsecase) {
        this.transferUsecase = transferUsecase;
        this.customerUsecase = customerUsecase;
        this.accountUsecase = accountUsecase;
    }

    @GetMapping("/newtransfer")
    public String newtransfer(Model model) {
        Customer customer = customerUsecase.getCustomer();
        var accounts = accountUsecase.getAllByCustomerId(customer.id());
        System.out.println(customer);
        model.addAttribute("accounts", accounts);
        return "newtransfer";
    }

    @PostMapping("/newtransfer")
    public String newtransferPost(NewTransferDto transferDto, Model model) {
        try {
            transferUsecase.transferMoney(transferDto.RecipientBankAccountId(), transferDto.RecipientBankAccountId(),
                    transferDto.amount());
        } catch (InsufficientFundsException e) {
            return "redirect:/transferErrorBalance";
        } catch (AccountDoesntExistExeption e) {
            return "redirect:/transferErrorNums";
        }

        return "transferOk";
    }

    // Нужно прописать три варианта - если баланс ниже суммы перевода, то страница
    // "transferErrorBalance.html",
    // если реквизиты неверны, то страница "transferErrorNums.html"
    // в случае успеха снимаются деньги и производится переход на страницу
    // "transferOk.html"

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
    public String withdraftPost(NewWithdrawalDto withdrawalDto, Model model) {
        try {
            transferUsecase.withdrawMoney(withdrawalDto.bankAccountId(), withdrawalDto.amount());
        } catch (InsufficientFundsException e) {
            return "redirect:/withdraftError";
        }
        return "withdraftOk";
    }

    // Нужно прописать два варианта - если баланс ниже суммы перевода, то страница
    // "withdraftError.html",
    // в случае успеха снимаются деньги и производится переход на страницу
    // "withdraftOk.html"

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
        Customer customer = customerUsecase.getCustomer();
        var accounts = accountUsecase.getAllByCustomerId(customer.id());
        model.addAttribute("accounts", accounts);
        return "newdeposit";
    }

    @PostMapping("/newdeposit")
    public String newdepositPost(NewRefillDto newRefillDto, Model model) {
        transferUsecase.refillMoney(newRefillDto.bankAccountId(), newRefillDto.amount());
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
