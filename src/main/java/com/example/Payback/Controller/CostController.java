package com.example.Payback.Controller;

import com.example.Payback.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class CostController {

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
