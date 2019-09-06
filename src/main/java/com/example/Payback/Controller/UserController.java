package com.example.Payback.Controller;

import com.example.Payback.Cost;
import com.example.Payback.PaybackGroup;
import com.example.Payback.Service.GroupService;
import com.example.Payback.Service.UserService;
import com.example.Payback.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private GroupService groupService;

    @GetMapping("/adduser")
    public String createUser(Model model) {
        model.addAttribute("user", new User());
        return "PBCreateUser";
    }

    @PostMapping("/adduser")
    public String addUser(@ModelAttribute User user, Model model, @RequestParam String repPassword, HttpSession httpSession) {
<<<<<<< HEAD
=======

>>>>>>> a40a256198174ddb473d9fffb17dbb5df609bc95
        httpSession.removeAttribute("result");

        if (repPassword.equals(user.getPassword())) {
            String result = userService.addUser(user);
            httpSession.setAttribute("result", result);
<<<<<<< HEAD

=======
>>>>>>> a40a256198174ddb473d9fffb17dbb5df609bc95
            if (result.equals("OK")) {
                return "redirect:/login";
            } else {
                model.addAttribute("user", user);
                return "PBCreateUser";
            }
        } else {
            String result = "password";
            httpSession.setAttribute("result", result);
        }
        return "PBCreateUser";
    }

    @GetMapping("/user")
    public String userPage(HttpSession httpSession) throws Exception {
        User user = groupService.getLoggedinUser();
        Long id = user.getId();
        List<PaybackGroup> groups = groupService.getGroupListByUserId(id);
        List<Cost> costs = userService.getAllLoggedInUsersCosts();
        List<String> payments = userService.getAllLoggedInUsersPayments();
        httpSession.setAttribute("groups", groups);
        httpSession.setAttribute("loggedinUser", user);
        httpSession.setAttribute("costs", costs);
        httpSession.setAttribute("payments", payments);
        return "PBUserProfile";
    }
}
