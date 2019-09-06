package com.example.Payback.Service;

import com.example.Payback.Cost;
import com.example.Payback.GroupMember;
import com.example.Payback.PaybackGroup;
import com.example.Payback.Repository.CostRepository;
import com.example.Payback.Repository.GroupMemberRepository;
import com.example.Payback.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CostService {

    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private CostRepository costRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private GroupService groupService;

    public List<String> getCostDescriptionsForGroup(Long id) {
        PaybackGroup group = groupService.getGroupById(id);
        List<String> costDescriptions = new ArrayList<>();
        List<GroupMember> groupMembers = group.getGroupMembers();
        for (GroupMember member : groupMembers) {
            List<Cost> costs = member.getCosts();
            for(Cost cost : costs) {
                costDescriptions.add(cost.getGroupMember().getUser().getUserName() + " paid " + cost.getCost() + " kr for " + cost.getType() + ".");
            }
        }

        return costDescriptions;
    }

    public GroupMember getGroupMember(Long userId, Long groupId){
        return groupMemberRepository.findByUserIdAndPaybackGroupId(userId, groupId);
    }

    public void saveCost(Cost cost){
        costRepository.save(cost);
    }

    public List<Cost> getCostsForGroupMembersByGroupId(long groupId){
        List<GroupMember> groupMembers = groupService.getGroupMembers(groupId);
        List<Cost> groupCosts = new ArrayList<>();
        for(GroupMember groupMember : groupMembers) {
            groupCosts.addAll(costRepository.findByGroupMemberId(groupMember.getId()));
        }
        return groupCosts;
    }

}
