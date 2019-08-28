package com.example.Payback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PostMapping("/login")
    public void login() {

    }

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/")
    public String addUser(User user) {
        String result = checkUser(user);
        if (result.equals("username") || result.equals("email") || result.equals("phoneNr")) {
            return result;
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            return result;
        }
    }

    private String checkUser(User user) {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            return "username";
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "email";
        }
        if (userRepository.findByPhoneNr(user.getPhoneNr()).isPresent()) {
            return "phoneNr";
        }
        return "Added user";
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }
}
