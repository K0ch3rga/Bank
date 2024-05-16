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
    private final CustomerUsecase customerUsecase;

    @Autowired
    public CustomerController(CustomerUsecase customerUsecase) {
        this.customerUsecase = customerUsecase;

    }

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

    @GetMapping("/login")
    public String getLoginPage(Model model) {
        return "login";
    }

    @GetMapping("/main")
    public String main(Model model) {
        model.addAttribute("user", customerUsecase.getCustomer());
        return "main";
    }
}



