package com.example.Payback.Controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SecurityController {

    @GetMapping("/home")
    public String home () {
        return "PBinloggad";
    }

    @GetMapping ({"/", "/start", "login"})
    public String login () {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            return "redirect:/home";
        }
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
