package com.example.Payback.Service;

import com.example.Payback.GroupMember;
import com.example.Payback.PaybackGroup;
import com.example.Payback.Repository.GroupMemberRepository;
import com.example.Payback.Repository.UserRepository;
import com.example.Payback.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GroupMemberService {
    @Autowired
    GroupMemberRepository groupMemberRepository;
    @Autowired
    UserRepository userRepository;

    //String messages for adding users
    private String couldNotFindUser = "Could not find the user in the database.";
    private String userAlreadyAdded = "The user has already been added to the group.";
    private String successfullyAdded = " has been added to the group";
    public String getCouldNotFindUser() {
        return couldNotFindUser;
    }
    public String getUserAlreadyAdded() {
        return userAlreadyAdded;
    }
    public String getSuccessfullyAdded() {
        return successfullyAdded;
    }

    public List<GroupMember> saveUsersAsGroupMembers(List<User> users, PaybackGroup group) {
        List<GroupMember> gmList = new ArrayList<>();
        for (User user : users) {
            if (user != null) {
                GroupMember gm = new GroupMember(user, group);
                gmList.add(gm);
                groupMemberRepository.save(gm);
            }
        }
        return gmList;
    }

    public String checkAddNewGroupMember(User user, List<User> users) {
        if (user == null) {
            return couldNotFindUser;
        }
        for (User tempUser : users) {
            if (user.getUserName().equals(tempUser.getUserName())) {
                return userAlreadyAdded;
            }
        }
        return user.getUserName() + successfullyAdded;
    }
}
