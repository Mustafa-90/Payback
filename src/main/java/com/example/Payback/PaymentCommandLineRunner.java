//package com.example.Payback;
//
//import com.example.Payback.Repository.CostRepository;
//import com.example.Payback.Repository.GroupMemberRepository;
//import com.example.Payback.Repository.PaybackGroupRepository;
//import com.example.Payback.Repository.PaymentRepository;
//import com.example.Payback.Service.PaymentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import javax.transaction.Transactional;
//import java.util.*;
//
//@Component
//@Transactional
//public class PaymentCommandLineRunner implements CommandLineRunner {
//
//    @Autowired
//    PaybackGroupRepository paybackGroupRepository;
//
//    @Autowired
//    GroupMemberRepository groupMemberRepository;
//
//    @Autowired
//    CostRepository costRepository;
//
//    @Autowired
//    PaymentRepository paymentRepository;
//
//    @Autowired
//    PaymentService paymentService;
//
//    @Override
//    public void run(String... args) throws Exception {
//        paymentService.createPaymentsForAGroup(6L);
//    }
//}
//
