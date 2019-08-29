package com.example.Payback.Service;

import com.example.Payback.Repository.UserRepository;
import com.example.Payback.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;


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

    public void updateUser (User user) {
        userRepository.save(user);
    }

    public String checkUser(User user) {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            return "userName";
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "email";
        }
        if (userRepository.findByPhoneNr(user.getPhoneNr()).isPresent()) {
            return "phoneNr";
        }
        return "OK";
    }

    public String logIn(String userName, String password){
        User user = userRepository.findByUserName("user").get();
        if (user == null) {
            user = new User();
            user.setUserName(userName);
            user.setPassword(encoder.encode(password));
            userRepository.save(user);
        }

        return "ok";
    }
}
