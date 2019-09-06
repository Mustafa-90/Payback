package com.example.Payback.Controller;

import com.example.Payback.Cost;
import com.example.Payback.GroupMember;
import com.example.Payback.PaybackGroup;
import com.example.Payback.Service.CostService;
import com.example.Payback.Service.GroupMemberService;
import com.example.Payback.Service.GroupService;
import com.example.Payback.Service.PaymentService;
import com.example.Payback.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.List;

@Controller
@Transactional
public class CostController {

    @Autowired
    private CostService costService;
    @Autowired
    private GroupService groupService;
    @Autowired
    private GroupMemberService groupMemberService;
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/addCost")
    public String addCost(HttpSession httpSession, @RequestParam Double cost, @RequestParam String type) throws Exception {
        Long id = (Long) httpSession.getAttribute("groupId");
        PaybackGroup group = groupService.getGroupById(id);
        User user = groupService.getLoggedinUser();
        GroupMember creator = costService.getGroupMember(user.getId(), group.getId());
        costService.saveCost(new Cost(creator, cost, type));
        paymentService.createPaymentsForAGroup(id);
        List<String> groupPayments = paymentService.getPaymentDescriptionsForGroup(id);
        httpSession.setAttribute("groupPayments", groupPayments);
        httpSession.setAttribute("creator", creator);
        return "redirect:/group/" + id;
    }


}