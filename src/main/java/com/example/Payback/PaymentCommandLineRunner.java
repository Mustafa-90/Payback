//package com.example.Payback;
//
//import com.example.Payback.Repository.CostRepository;
//import com.example.Payback.Repository.GroupMemberRepository;
//import com.example.Payback.Repository.PaybackGroupRepository;
//import com.example.Payback.Repository.PaymentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import javax.transaction.Transactional;
//import java.util.List;
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

//    @Override
//    public void run(String... args) throws Exception {
//        List<GroupMember> listOfCost = groupMemberRepository.findByPaybackGroupId(2L);
//        double sum = 0;
//
//        for (int i = 0; i < listOfCost.size(); i++) {
//            for (int j = 0; j < listOfCost.get(i).getCosts().size(); j++) {
//                sum = sum + listOfCost.get(i).getCosts().get(j).getCost();
//            }
//        }
//        listOfCost.get(0).getPaybackGroup().setTotalSum(sum);
//        paybackGroupRepository.save(listOfCost.get(0).getPaybackGroup());
//    }

//    @Override
//    public void run(String... args) throws Exception {
////        GroupMember groupMember = groupMemberRepository.findById(2L).get();
////        Cost cost = new Cost(groupMember, 1000.0, "Mat", null);
////        costRepository.save(cost);
//
//        Cost cost = costRepository.findById(11L).get();
//        for (int i = 0; i < cost.getGroupMember().getPaybackGroup().getGroupMembers().size(); i++) {
//            System.out.println(cost.getGroupMember().getPaybackGroup().getGroupMembers().get(i).getUser().getFirstName());
//        }
//        System.out.println(cost.getCost());
//        double splitCost = cost.getCost() / cost.getGroupMember().getPaybackGroup().getGroupMembers().size();
//        System.out.println(splitCost);
//
//        for (int i = 0; i < cost.getGroupMember().getPaybackGroup().getGroupMembers().size(); i++) {
//            if (cost.getGroupMember().getUser().getId() != cost.getGroupMember().getPaybackGroup().getGroupMembers().get(i).getUser().getId()) {
//                paymentRepository.save(new Payment(cost, false, cost.getGroupMember().getPaybackGroup().getGroupMembers().get(i).getUser().getId(), splitCost));
//            }
//        }
//    }
//}
