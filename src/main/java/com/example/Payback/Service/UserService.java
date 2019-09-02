package com.example.Payback.Service;

import com.example.Payback.GroupMember;
import com.example.Payback.Repository.UserRepository;
import com.example.Payback.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;


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

    public void updateUser(User user) {
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

    public String checkUser(String identifier) {
        if (userRepository.findByUserName(identifier).isPresent()) {
            return "userName";
        }
        if (userRepository.findByEmail(identifier).isPresent()) {
            return "email";
        }
        if (userRepository.findByPhoneNr(identifier).isPresent()) {
            return "phoneNr";
        }
        return "Error";
    }

    public User getUserByIdentifier(String identifier) {
        User user;
        String foundUser = checkUser(identifier);
        switch (foundUser) {
            case "userName":
                user = userRepository.findByUserName(identifier).get();
                break;
            case "email":
                user = (User) userRepository.findByEmail(identifier).get();
                break;
            case "phoneNr":
                user = (User) userRepository.findByPhoneNr(identifier).get();
                break;
            //OBS! Kan detta lösas på annat sätt? Kolla om user är null och i så fall skriva ut att användaren inte existerar
            default:
                return new User();
        }
        return user;
    }
}