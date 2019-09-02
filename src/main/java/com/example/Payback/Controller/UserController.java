package com.example.Payback.Controller;

import com.example.Payback.Service.UserService;
import com.example.Payback.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/adduser")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "PBCreateUser";
    }

<<<<<<< HEAD
    @GetMapping("/")
    public String init() {
        return "PButloggad";
    }

=======
>>>>>>> 3e486979b213c32426b733aa134304db44acd68d
    @PostMapping("/adduser")
    public String addUser(@ModelAttribute User user, Model model, @RequestParam String repPassword) {

        if (repPassword.equals(user.getPassword())) {
            String result = userService.addUser(user);
            if (result.equals("OK")) {
                return "redirect:/login";
            } else {
                model.addAttribute("user", user);
                return "PBCreateUser";
            }
<<<<<<< HEAD
        } else {
            model.addAttribute("repPassword", "Wrong");
            return "PBCreateUser";
        }
    }


}
=======
        }
        return "PBCreateUser";
    }
}
>>>>>>> 3e486979b213c32426b733aa134304db44acd68d
