package com.example.Payback.Controller;

import com.example.Payback.Repository.*;
import com.example.Payback.Service.UserService;
import com.example.Payback.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/test")
    public String init() {
        return "PBinloggad";
    }

    @GetMapping({"/login", "/start", "/home"})
    public String tryLogin() {
        return "PButloggad";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User result = userRepository.findByUserName(user.getUserName()).get();
        if (result.getPassword() == user.getPassword()) {
            return "PBinloggad";
        } else {
            return "PButloggad";
        }
    }

    @GetMapping("/")
    public String inits() {
        return "PButloggad";
    }

}
