package com.example.Payback.Service;

import com.example.Payback.GroupMember;
import com.example.Payback.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private GroupService groupService;
    @Autowired
    private UserService userService;

    public double calcTotalSumForGroup(List<GroupMember> listOfCostsByGroup) {
        double totalSum = 0;
        for (int i = 0; i < listOfCostsByGroup.size(); i++) {
            for (int j = 0; j < listOfCostsByGroup.get(i).getCosts().size(); j++) {
                totalSum = totalSum + listOfCostsByGroup.get(i).getCosts().get(j).getCost();
            }
        }
        return totalSum;
    }
    public LinkedHashMap<User, Double>  calcMembersBalance (double totalSum, List<GroupMember> listOfCostsByGroup) {
        double averageSum = totalSum / listOfCostsByGroup.get(0).getPaybackGroup().getGroupMembers().size();
        LinkedHashMap<User, Double> costMapping = new LinkedHashMap<>();
        for (int i = 0; i < listOfCostsByGroup.size(); i++) {
            double owedMoney = 0;
            double usersTotalCost = 0;
            for (int j = 0; j < listOfCostsByGroup.get(i).getCosts().size(); j++) {
                usersTotalCost += listOfCostsByGroup.get(i).getCosts().get(j).getCost();
            }
            owedMoney = usersTotalCost - averageSum;
            costMapping.put(listOfCostsByGroup.get(i).getUser(), owedMoney);
        }
        return costMapping;
    }

    public Double getMemberBalancePerMember(Long groupId, Long userId) {
        List<GroupMember> listOfCostsByGroup = groupService.getGroupMembers(groupId);
        double totalSum = calcTotalSumForGroup(listOfCostsByGroup);
        LinkedHashMap<User, Double> map = calcMembersBalance(totalSum, listOfCostsByGroup);
        User user = userService.getUserById(userId);
        return map.get(user);
    }
}
