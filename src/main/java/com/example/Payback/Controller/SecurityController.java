package com.example.Payback.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {


    @GetMapping("/payback")
    public String inloggad() {
        return "PBinloggad";
    }

    @GetMapping("/home")
    public String home() {
        return "PBinloggad";
    }

    @GetMapping({"/", "/start", "login"})
    public String login() {
        return "PButloggad";
    }
<<<<<<< HEAD
}
=======

    @GetMapping("/contact")
    public String contact() {
        return "PBcontact";
    }

    @GetMapping("/policy")
    public String policy() {
        return "PBpolicy";
    }

    @GetMapping("/about")
    public String about() {
        return "PBabout";
    }
}
>>>>>>> 64edc1300c8f3a82f02e58c7ce8a3f714e30dca8
