package com.example.Payback.Controller;

import com.example.Payback.PaybackGroup;
import com.example.Payback.Service.CostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CostController {

    @Autowired
    CostService costService;


    @GetMapping("/testTotalSum")
    public String getPaymentPerMember(@ModelAttribute PaybackGroup group) {
        String sum = costService.calculateCost(2L);
        return sum;
    }

}
