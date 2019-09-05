package com.example.Payback.Service;

import com.example.Payback.*;
import com.example.Payback.Repository.UserRepository;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private GroupService groupService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private CostService costService;


    public String addUser(User user) {
        String result = checkUser(user);
        if (result.equals("userName") || result.equals("email") || result.equals("phoneNr")) {
            return result;
        } else {
            user.setPassword(encoder.encode(user.getPassword()));
            userRepository.save(user);
            return result;
        }
    }

    public void updateUser(User user) {
        userRepository.save(user);
    }

    public String checkUser(User user) {
        if (userRepository.findByUserName(user.getUserName()).isPresent()) {
            return "userName";
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "email";
        }
        if (userRepository.findByPhoneNr(user.getPhoneNr()).isPresent()) {
            return "phoneNr";
        }
        return "OK";
    }

    public String checkUser(String identifier) {
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

    public User getUserByIdentifier(String identifier) {
        User user;
        String foundUser = checkUser(identifier);
        switch (foundUser) {
            case "userName":
                user = userRepository.findByUserName(identifier).get();
                break;
            case "email":
                user = (User) userRepository.findByEmail(identifier).get();
                break;
            case "phoneNr":
                user = (User) userRepository.findByPhoneNr(identifier).get();
                break;
            default:
                return null;
        }
        return user;
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public List<PaybackGroup> getAllUsersGroups() throws Exception {
        User user = groupService.getLoggedinUser();
        List<GroupMember> groupMembers = user.getGroupMembers();
        List<PaybackGroup> paybackGroups = new ArrayList<>();
        for (GroupMember groupMember : groupMembers) {
            paybackGroups.add(groupMember.getPaybackGroup());
        }
        return paybackGroups;
    }

    public List<Cost> getAllLoggedInUsersCosts() throws Exception {
        List<Cost> costs = new ArrayList<>();
        List<PaybackGroup> paybackGroups = getAllUsersGroups();
        for (PaybackGroup group : paybackGroups) {
            costs.addAll(costService.getCostsForGroupMembersByGroupId(group.getId()));
        }
        return costs;
    }

    public List<String> getAllLoggedInUsersPayments() throws Exception {
        User user = groupService.getLoggedinUser();
        List<String> payments = new ArrayList<>();
        List<PaybackGroup> paybackGroups = getAllUsersGroups();
        for (PaybackGroup group : paybackGroups) {
            payments.addAll(paymentService.getPaymentDescriptionsForGroup(group.getId()));
        }
        if (payments.size() > 0) {
            for (String payment : payments) {
                if (!payment.contains("from " + user.getUserName())) {
                    payments.remove(payment);
                }
            }
        }
        return payments;
    }


}