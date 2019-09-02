package com.example.Payback.Controller;

import com.example.Payback.PaybackGroup;
import com.example.Payback.User;
import com.example.Payback.Service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaybackGroupController {

    @Autowired
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
}
