package com.example.Payback.Controller;

import com.example.Payback.Repository.*;
import com.example.Payback.Service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PaymentController {

    @Autowired
    CostService costService;

    @GetMapping("/addCost")
    public String addCostToGroup() {

        //costService.addNewCost();
        return "hej";
    }

}
