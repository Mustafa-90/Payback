package com.example.Payback.Service;

import com.example.Payback.Cost;
import com.example.Payback.GroupMember;
import com.example.Payback.PaybackGroup;
import com.example.Payback.User;
import com.example.Payback.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CostService {

    @Autowired
    CostRepository costRepository;
    @Autowired
    GroupMemberRepository groupMemberRepository;
    @Autowired
    PaybackGroupRepository paybackGroupRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    UserRepository userRepository;


    public String calculateCost(long id) {
        List<GroupMember> members = groupMemberRepository.findByPaybackGroupId(id);
        Double totalSum = paybackGroupRepository.findById(id).get().getTotalSum();
        Double calculatedCost = totalSum / members.size();


        return "" + calculatedCost;
    }


    public String addCostToGroup(int cost, PaybackGroup paybackGroup, User user) {
        Cost newCost = new Cost();
        return "hej";
    }
}
