package com.example.Payback.Service;

import com.example.Payback.GroupMember;
import com.example.Payback.PaybackGroup;
import com.example.Payback.Repository.GroupMemberRepository;
import com.example.Payback.Repository.GroupRepository;
import com.example.Payback.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GroupService {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private GroupMemberRepository groupMemberRepository;

    public void addGroup(PaybackGroup group) {
        groupRepository.save(group);
    }

    public void addGroupMember(PaybackGroup group, String identifier) {
        User user = userService.getUserByIdentifier(identifier);
        GroupMember groupMember = new GroupMember(user, group);
        groupMemberRepository.save(groupMember);
    }

    public User getLoggedinUser() throws Exception {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName;
        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userService.getUserByIdentifier(userName);
    }

    public List<User> getOrCreateUserListWithCreator(HttpSession httpSession) throws Exception {
        List<User> uList = (List) httpSession.getAttribute("userList");
        PaybackGroup group = (PaybackGroup) httpSession.getAttribute("group");
        if (uList == null) {
            List<User> users = new ArrayList<>();
            users.add(getLoggedinUser());
            httpSession.setAttribute("userList", users);
            uList = (List) httpSession.getAttribute("userList");
        }
        return uList;
    }

    public void cancelGroup(PaybackGroup group) {
        groupRepository.delete(group);
    }
}
