package com.example.Payback.Service;

import com.example.Payback.GroupMember;
import com.example.Payback.PaybackGroup;
import com.example.Payback.Repository.GroupMemberRepository;
import com.example.Payback.Repository.GroupRepository;
import com.example.Payback.Repository.PaybackGroupRepository;
import com.example.Payback.Repository.UserRepository;
import com.example.Payback.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private PaybackGroupRepository paybackGroupRepository;

    public void addGroup(PaybackGroup group) {
        groupRepository.save(group);
    }

    public String checkGroupMember(String identifier) {
        if (userRepository.findByUserName(identifier).isPresent()) {
            return "userName";
        }
        if (userRepository.findByEmail(identifier).isPresent()) {
            return "email";
        }
        if (userRepository.findByPhoneNr(identifier).isPresent()) {
            return "phoneNr";
        }
        return "Error";
    }

    public void addGroupMember(PaybackGroup group, String identifier) {
        User user;
        String foundUser = checkGroupMember(identifier);
        switch(foundUser) {
            case "userName":
                user = userRepository.findByUserName(identifier).get();
                break;
            case "email":
                user = (User)userRepository.findByEmail(identifier).get();
                break;
            case "phoneNr":
                user = (User)userRepository.findByPhoneNr(identifier).get();
                break;
            default:
                return;
        }
//        GroupMember groupMember = new GroupMember(user, group);
//        groupMemberRepository.save(groupMember);
    }

    public void addCostsForGroup() {
        List<GroupMember> listOfCost = groupMemberRepository.findByPaybackGroupId(2L);
        double sum = 0;

        for (int i = 0; i < listOfCost.size(); i++) {
            for (int j = 0; j < listOfCost.get(i).getCosts().size(); j++) {
                sum = sum + listOfCost.get(i).getCosts().get(j).getCost();
            }
        }

        System.out.println(listOfCost.get(0).getPaybackGroup().getTotalSum());
        listOfCost.get(0).getPaybackGroup().setTotalSum(sum);

        System.out.println(listOfCost.get(0).getPaybackGroup().getTotalSum());
        System.out.println(listOfCost.get(0).getPaybackGroup().getGroupName());
        listOfCost.get(0).getPaybackGroup().setGroupName("HEJSAN");

//		PaybackGroup paybackGroup = new PaybackGroup(listOfCost.get(0).getPaybackGroup().getId(), listOfCost.get(0).getPaybackGroup().getCreator(), listOfCost.get(0).getPaybackGroup().getGroupName(), listOfCost.get(0).getPaybackGroup().getTotalSum());

        paybackGroupRepository.save(listOfCost.get(0).getPaybackGroup());

        PaybackGroup paybackGroup = paybackGroupRepository.findById(2L).get();
        System.out.println(paybackGroup.getTotalSum() + " " + paybackGroup.getGroupName());

    }
}
