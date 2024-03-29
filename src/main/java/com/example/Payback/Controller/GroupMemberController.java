package com.example.Payback.Controller;

import com.example.Payback.GroupMember;
import com.example.Payback.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class GroupMemberController {

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

}
