package bank.Adapters.in;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BaseController {

    @GetMapping("/status")
    @ResponseBody
    public String ok() {
        return "OK";
    }
    
}
