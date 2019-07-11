package com.example.ProvinceDistrict.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {

    @RequestMapping(value = {"/", "/home"})
    public String greet(){
        return "home";
    }
}
