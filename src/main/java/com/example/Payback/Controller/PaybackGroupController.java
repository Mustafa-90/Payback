package com.example.Payback.Controller;

import com.example.Payback.GroupMember;
import com.example.Payback.PaybackGroup;
import com.example.Payback.Payment;
import com.example.Payback.Service.*;
import com.example.Payback.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.LinkedHashMap;
import java.util.List;

@Controller
@Transactional
public class PaybackGroupController {

    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupMemberService groupMemberService;
    @Autowired
    private CostService costService;
    @Autowired
    private PaymentService paymentService;

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
        List<String> groupPayments = paymentService.getPaymentDescriptionsForGroup(id);
        httpSession.setAttribute("groupPayments", groupPayments);
        paymentService.createPaymentsForAGroup(id);
        List<GroupMember> groupMembers = groupService.getGroupMembers(id);
        String groupName = groupService.getGroupById(id).getGroupName();
        List<String> costs = costService.getCostDescriptionsForGroup(id);
        double totalCost = paymentService.calcTotalSumForGroup(groupMembers);
        LinkedHashMap<User, Double> tempMemberBalances = paymentService.calcMembersBalance(totalCost, groupMembers);
        LinkedHashMap<User, Integer> memberBalances = paymentService.memberBalanceToInt(tempMemberBalances);
        List<Payment> paymentsGroup = paymentService.getPaymentForGroup(id);
        LinkedHashMap<Payment, String> paymentsWithDesc = paymentService.getPaymentDescriptionsForGroupAndPaymentId(id);
        httpSession.setAttribute("paymentList", paymentsWithDesc);
        httpSession.setAttribute("groupMembers", groupMembers);
        httpSession.setAttribute("groupName", groupName);
        httpSession.setAttribute("groupId", id);
        httpSession.setAttribute("costDescriptions", costs);
        httpSession.setAttribute("totalCost", totalCost);
        httpSession.setAttribute("memberBalances", memberBalances);

        return "PBOneGroup";
    }

    @GetMapping("/groups")
    public String groupsPage(HttpSession httpSession) throws Exception {
        Long id = groupService.getLoggedinUserId();
        List<PaybackGroup> groups = groupService.getGroupListByUserId(id);
        httpSession.setAttribute("groups", groups);
        return "PBViewGroups";
    }

    @PostMapping("/addgroupmember")
    public String postGroupMember(@RequestParam String identifier, HttpSession httpSession) throws Exception {
        httpSession.removeAttribute("result");
        groupService.getOrCreateUserListWithCreator(httpSession);
        PaybackGroup group = (PaybackGroup) httpSession.getAttribute("group");
        List<User> users = (List) httpSession.getAttribute("userList");
        User user = userService.getUserByIdentifier(identifier);
        String result = groupMemberService.checkAddNewGroupMember(user, users);
        if (!result.contains(groupMemberService.getSuccessfullyAdded())) {
            httpSession.setAttribute("result", result);
        }
        if (result.contains(groupMemberService.getSuccessfullyAdded())) {
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

    @GetMapping("/group")
    public String showOneGroup() {
        return "PBOneGroup";
    }

    @GetMapping("/profile")
    public String profile() {
        return "PBUserProfiel";
    }
}
