package com.example.Payback.Controller;

import com.example.Payback.Repository.*;
import com.example.Payback.PaybackGroup;
import com.example.Payback.Service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PaybackGroupController {

    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private GroupService groupService;

    @GetMapping("/addgroup")
    public String createGroup(Model model) {
        model.addAttribute("group", new PaybackGroup());
        return "PBCreateGroup";
    }

    @PostMapping("/addgroup")
    public String addGroup(@ModelAttribute PaybackGroup group, Model model, @RequestParam String identifier) {
        model.addAttribute("identifier", identifier);
        groupService.addGroup(group);
        return "PBCreateGroup";
    }
}
