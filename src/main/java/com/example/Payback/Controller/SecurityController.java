package com.example.Payback.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

<<<<<<< HEAD
    @GetMapping("/login")
    public String addUser() {
        return "PButloggad";
    }

    @GetMapping("/payback")
    public String inloggad() {
        return "PBinloggad";
=======
    @GetMapping("/home")
    public String home () {
        return "PBinloggad";
    }

    @GetMapping ({"/", "/start", "login"})
    public String login () {
        return "PButloggad";
>>>>>>> 3e486979b213c32426b733aa134304db44acd68d
    }
}