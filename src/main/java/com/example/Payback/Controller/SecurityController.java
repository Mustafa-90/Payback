package com.example.Payback.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/login")
    public String addUser () {
        return "PButloggad";
    }

    @GetMapping ("/payback")
    public String inloggad() {
        return "PBinloggad";
    }
}
