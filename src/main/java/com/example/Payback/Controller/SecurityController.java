package com.example.Payback.Service;

import org.springframework.web.bind.annotation.GetMapping;

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
