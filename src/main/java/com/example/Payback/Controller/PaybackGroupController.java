package com.example.Payback.Controller;

import com.example.Payback.GroupMember;
import com.example.Payback.PaybackGroup;
import com.example.Payback.Repository.GroupMemberRepository;
import com.example.Payback.Service.GroupMemberService;
import com.example.Payback.Service.GroupService;
import com.example.Payback.Service.UserService;
import com.example.Payback.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PaybackGroupController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupMemberService groupMemberService;

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
        List<GroupMember> gmList = groupMemberService.saveUsersAsGroupMembers(uList, group);
        group.setGroupMembers(gmList);
        Long id = groupService.addGroup(group);
        httpSession.removeAttribute("group");
        httpSession.removeAttribute("userList");
        httpSession.removeAttribute("result");
        return "redirect:/group/" + id;
    }

    @GetMapping("/group/{id}")
    public String groupInfo(@PathVariable Long id, HttpSession httpSession) {
        List<GroupMember> groupMembers = groupService.getGroupMembers(id);
        httpSession.setAttribute("groupMembers", groupMembers);
        return "PBOneGroup";
    }

    @PostMapping("/addgroupmember")
    public String postGroupMember(@RequestParam String identifier, HttpSession httpSession) throws Exception {
        httpSession.removeAttribute("result");
        groupService.getOrCreateUserListWithCreator(httpSession);
        PaybackGroup group = (PaybackGroup) httpSession.getAttribute("group");
        List<User> users = (List) httpSession.getAttribute("userList");
        User user = userService.getUserByIdentifier(identifier);
        String result = groupMemberService.checkAddNewGroupMember(user, users);
        if(!result.contains(groupMemberService.getSuccessfullyAdded())){
            httpSession.setAttribute("result", result);
        }
        if(result.contains(groupMemberService.getSuccessfullyAdded())) {
            users.add(user);
        }
        httpSession.setAttribute("userList", users);
        return "redirect:/addgroup";
    }

    @GetMapping("/removeGroup")
    public String removeGroup(HttpSession httpSession) {
        PaybackGroup group = (PaybackGroup) httpSession.getAttribute("group");
        groupService.cancelGroup(group);
        return "PBInloggad";
    }

    @GetMapping ("/groups")
    public String showGroup() {
        return "PBViewGroup";
    }

    @GetMapping ("/group")
    public String showOneGroup() {
        return "PBOneGroup";
    }

    @GetMapping ("/profile")
    public String profile() {
        return "PBUserProfiel";
    }
}
