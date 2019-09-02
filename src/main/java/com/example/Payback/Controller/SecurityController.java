package com.example.Payback.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController {

    @GetMapping("/home")
    public String home () {
        return "PBinloggad";
    }

    @GetMapping ({"/", "/start", "login"})
    public String login () {
        return "PButloggad";
    }

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
