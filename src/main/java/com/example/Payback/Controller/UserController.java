package com.example.Payback.Controller;

import com.example.Payback.Repository.*;
import com.example.Payback.Service.UserService;
import com.example.Payback.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping ("/")
    public String addUser () {

        User user = new User("Tommy", "123", "Tommy", "Ågren", "t@a.se", "789");

        String result = userService.addUser(user);

        return result;
    }

}
