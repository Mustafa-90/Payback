package com.example.Payback.Controller;

import com.example.Payback.Payment;
import com.example.Payback.Repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class PaymentController {

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

    @PostMapping ("/isPaybackd")
    public String isPaybackd (@RequestParam long paymentId, HttpSession httpSession) {

        long groupId = (long)httpSession.getAttribute("groupId");

        Payment payment = paymentRepository.findById(paymentId).get();
        payment.setPaybackd(true);
        paymentRepository.save(payment);

        return "redirect:/group/" + groupId;
    }
}
