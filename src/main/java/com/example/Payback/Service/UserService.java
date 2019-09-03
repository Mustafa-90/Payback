package com.example.Payback.Service;

import com.example.Payback.GroupMember;
import com.example.Payback.Repository.UserRepository;
import com.example.Payback.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
<<<<<<< HEAD
    UserRepository userRepository;
=======
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
>>>>>>> 64edc1300c8f3a82f02e58c7ce8a3f714e30dca8

    @PostMapping("/login")
    public void login() {

    }

<<<<<<< HEAD
        import org.springframework.stereotype.Service;

    @Service
    public class UserService {

        @Autowired
        UserRepository userRepository;
>>>>>>>8dc238fce23fd6cfa900d288e9c8fb2d1273ea6e:src/main/java/com/example/Payback/Service/UserService.java
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
=======
    public void updateUser(User user) {
        userRepository.save(user);
    }

    public String checkUser(User user) {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            return "userName";
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "email";
>>>>>>> 64edc1300c8f3a82f02e58c7ce8a3f714e30dca8
        }

        private String checkUser(User user) {

            public void updateUser (User user){
                userRepository.save(user);
            }

            public String checkUser (User user){
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

            public void updateUser (User user){
                userRepository.save(user);
            }
        }
<<<<<<< HEAD
=======
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
            default:
                return null;
        }
        return user;
    }
}
>>>>>>> 64edc1300c8f3a82f02e58c7ce8a3f714e30dca8
