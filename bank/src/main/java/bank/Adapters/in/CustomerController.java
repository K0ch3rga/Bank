package bank.Adapters.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import bank.Application.dto.NewCustomerDto;
import bank.Application.usecases.CustomerUsecase;

@Controller
public class CustomerController {
    @Autowired
    private CustomerUsecase customerUsecase;

    @GetMapping("/registration")
    public String registration(Model model) {
        return "registration";
    }

    @PostMapping("/registration")
    public String addCustomer(NewCustomerDto newCustomer, Model model) {
        try {
            customerUsecase.saveCustomer(newCustomer);
            return "redirect:/login";
        } catch (Exception ex) {
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }

    @GetMapping("/main")
    public String main(Model model) {
        return "main";
    }

    @GetMapping("/newbill")
    public String newbill(Model model) {
        return "newbill";
    }

    @GetMapping("/billadded")
    public String billadded(Model model) {
        return "billadded";
    }

    @GetMapping("/newtransfer")
    public String newtransfer(Model model) {
        return "newtransfer";
    }

    @PostMapping("/newtransfer")
    public String newtransferPost(Model model) {
        return "transferOk";
    }
    // Нужно прописать три варианта - если баланс ниже суммы перевода, то страница "transferErrorBalance.html",
    // если реквизиты неверны, то страница "transferErrorNums.html"
    // в случае успеха снимаются деньги и производится переход на страницу "transferOk.html"

    @GetMapping("/transferOk")
    public String transferOk(Model model) {
        return "transferOk";
    }

    @GetMapping("/transferErrorNums")
    public String transferErrorNums(Model model) {
        return "transferErrorNums";
    }

    @GetMapping("/transferErrorBalance")
    public String transferErrorBalance(Model model) {
        return "transferErrorBalance";
    }

    @GetMapping("/withdraft")
    public String withdraft(Model model) {
        return "withdraft";
    }

    @PostMapping("/withdraft")
    public String withdraftPost(Model model) {
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
    public String newdepositPost(Model model) {
        return "depositOk";
    }

    @GetMapping("/depositOk")
    public String depositOk(Model model) {
        return "depositOk";
    }

    @GetMapping("/checkbalance")
    public String checkbalance(Model model) { return "checkbalance"; }

    @GetMapping("/showbalance")
    public String showbalance(Model model) { return "showbalance"; }

    @GetMapping("/printbalance")
    public String printbalance(Model model) { return "printbalance"; }
}



