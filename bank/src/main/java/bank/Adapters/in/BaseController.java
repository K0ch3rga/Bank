package bank.Adapters.in;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bank.Application.usecases.CustomerUsecase;

@Controller
public class BaseController{
    @Autowired
    CustomerUsecase customerUsecase;

    @GetMapping("/status")
    @ResponseBody
    public String ok() {
        return "OK";
    }
}
