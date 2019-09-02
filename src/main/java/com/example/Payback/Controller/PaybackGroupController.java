package com.example.Payback.Controller;

<<<<<<< HEAD
import com.example.Payback.PaybackGroup;
import com.example.Payback.User;
import com.example.Payback.Service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
=======
import com.example.Payback.GroupMember;
import com.example.Payback.PaybackGroup;
import com.example.Payback.Service.GroupService;
import com.example.Payback.Service.UserService;
import com.example.Payback.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
>>>>>>> 3e486979b213c32426b733aa134304db44acd68d

@Controller
public class PaybackGroupController {

    @Autowired
<<<<<<< HEAD
    CostService costService;

    @GetMapping("/creategroup")
    public String createGroup() {
        return "pbcreategroup";
    }

    @PostMapping("/addgroup")
    public String addGroup(@ModelAttribute PaybackGroup paybackGroup) {
        return "PBGroup";
    }


    @PostMapping("/addCostToGroup")
    public void addCostToGroup(int cost, @ModelAttribute PaybackGroup paybackGroup, @ModelAttribute User user) {
        //costService.addCostToGroup(cost, paybackGroup);
    }
=======
    private GroupService groupService;
    @Autowired
    private UserService userService;

    @GetMapping("/addgroup")
    public String createGroup(HttpSession httpSession) {
        if (httpSession.getAttribute("group") == null) {
            PaybackGroup group = new PaybackGroup();
            httpSession.setAttribute("group", group);
            groupService.addGroup(group);
        }
        return "PBCreateGroup";
    }

    @PostMapping("/addgroup")
    public String addGroup(@RequestParam String groupName, HttpSession httpSession) throws Exception {
        PaybackGroup group = (PaybackGroup) httpSession.getAttribute("group");
        group.setCreator(groupService.getLoggedinUser());
        group.setGroupName(groupName);
        List<User> uList = groupService.getOrCreateUserListWithCreator(httpSession);
        List<GroupMember> gmList = new ArrayList<>();
        for (User user : uList) {
            if (user != null) {
                gmList.add(new GroupMember(user, group));
            }
        }
        group.setGroupMembers(gmList);
        groupService.addGroup(group);
        httpSession.removeAttribute("group");
        httpSession.removeAttribute("userList");
        return "PBCreateGroup";
    }

    @PostMapping("/addgroupmember")
    //kolla så inte group member redan är tillagd!!!
    public String postGroupMember(@RequestParam String identifier, HttpSession httpSession) throws Exception {
        groupService.getOrCreateUserListWithCreator(httpSession);
        PaybackGroup group = (PaybackGroup) httpSession.getAttribute("group");
        User user = userService.getUserByIdentifier(identifier);
        List<User> users = (List)httpSession.getAttribute("userList");
        users.add(user);
        httpSession.setAttribute("userList", users);
        return "redirect:/addgroup";
    }
    //Om man trycker på cancel: ta bort hela gruppen i databasen (+cascade???)
>>>>>>> 3e486979b213c32426b733aa134304db44acd68d
}
