package bank.Adapters.in;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bank.Application.dto.NewCustomerDto;
import bank.Application.usecases.CustomerUsecase;

@Controller
@ResponseBody
public class CustomerController {
    @Autowired
    private CustomerUsecase customerUsecase;


    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }
    
    @PostMapping("/registration")
    public String addCustomer(NewCustomerDto newCustomer, Model model)
    {
        try
        {
            customerUsecase.saveCustomer(newCustomer);
            return "redirect:/login";
        }
        catch (Exception ex)
        {
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }
}
