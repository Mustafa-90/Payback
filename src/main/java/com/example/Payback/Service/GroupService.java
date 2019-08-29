package com.example.Payback.Service;

import com.example.Payback.GroupMember;
import com.example.Payback.PaybackGroup;
import com.example.Payback.Repository.GroupMemberRepository;
import com.example.Payback.Repository.GroupRepository;
import com.example.Payback.Repository.UserRepository;
import com.example.Payback.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;

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

    public void addGroupMember(User user, PaybackGroup group) {
        GroupMember groupMember = new GroupMember(user, group);
        groupMemberRepository.save(groupMember);
    }
}
