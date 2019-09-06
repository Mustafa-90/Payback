package com.example.Payback.Service;

import com.example.Payback.Cost;
import com.example.Payback.GroupMember;
import com.example.Payback.Payment;
import com.example.Payback.Repository.*;
import com.example.Payback.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class PaymentService {

    @Autowired
    private PaybackGroupRepository paybackGroupRepository;
    @Autowired
    private GroupMemberRepository groupMemberRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CostService costService;
    @Autowired
    private GroupMemberService groupMemberService;

    public void createPaymentsForAGroup(long id) {
        List<GroupMember> listOfCostsByGroup = groupMemberRepository.findByPaybackGroupId(id);

        for (int i = 0; i < listOfCostsByGroup.size(); i++) {
            for (int j = 0; j < listOfCostsByGroup.get(i).getCosts().size(); j++) {
                for (int k = 0; k < listOfCostsByGroup.get(i).getCosts().get(j).getPayments().size(); k++) {
                    if (!listOfCostsByGroup.get(i).getCosts().get(j).getPayments().get(k).isPaybackd()) {
                        paymentRepository.deleteById(listOfCostsByGroup.get(i).getCosts().get(j).getPayments().get(k).getId());
                    }
                }
            }
        }

        double totalSum = calcTotalSumForGroup(listOfCostsByGroup);

            listOfCostsByGroup.get(0).getPaybackGroup().setTotalSum(totalSum);
            paybackGroupRepository.save(listOfCostsByGroup.get(0).getPaybackGroup());

            LinkedHashMap<User, Double> costMapping = calcMembersBalance(totalSum, listOfCostsByGroup);

            for (int i = 0; i < costMapping.size(); i++) {
                for (int j = i + 1; j < costMapping.size(); j++) {
                    if (costMapping.containsKey(listOfCostsByGroup.get(i).getUser())) {
                        if (costMapping.get(listOfCostsByGroup.get(i).getUser()) + costMapping.get(listOfCostsByGroup.get(j).getUser()) == 0) {
                            if (costMapping.get(listOfCostsByGroup.get(i).getUser()) < 0) {
                                createEqualPayment(listOfCostsByGroup.get(j).getCosts(), listOfCostsByGroup.get(i).getUser().getId(), costMapping.get(listOfCostsByGroup.get(j).getUser()));
                                costMapping.remove(listOfCostsByGroup.get(i).getUser());
                                costMapping.remove(listOfCostsByGroup.get(j).getUser());
                            } else {
                                createEqualPayment(listOfCostsByGroup.get(i).getCosts(), listOfCostsByGroup.get(j).getUser().getId(), costMapping.get(listOfCostsByGroup.get(i).getUser()));
                                costMapping.remove(listOfCostsByGroup.get(i).getUser());
                                costMapping.remove(listOfCostsByGroup.get(j).getUser());
                            }
                        }
                    }
                }
            }

            orderMapByValue(costMapping);
            if (costMapping.size() > 0 && totalSum != 0) {
                createDividedPayment(costMapping, listOfCostsByGroup);
            }
    }

    public List<Payment> getPaymentsForAGroup(Long groupId) {
        List<Cost> costs = costService.getCostsForGroupMembersByGroupId(groupId);
        List<Payment> payments = new ArrayList<>();
        for (Cost cost : costs) {
            payments.addAll(cost.getPayments());
        }
        return payments;
    }

    public List<String> getPaymentDescriptionsForGroup(Long groupId) {
        List<String> paymentDescriptions = new ArrayList<>();
        List<Payment> payments = getPaymentsForAGroup(groupId);
<<<<<<< HEAD
        for (Payment payment : payments) {
=======

        for(Payment payment : payments) {
>>>>>>> a40a256198174ddb473d9fffb17dbb5df609bc95
            User userFrom = userRepository.findById(payment.getPayerId()).get();
            User userTo = payment.getCost().getGroupMember().getUser();
            paymentDescriptions.add((int)payment.getSum() + " kr from " + userFrom.getUserName() + " to " + userTo.getUserName());
        }
        return paymentDescriptions;
    }

    public List<Payment> getPaymentForGroup(Long groupId) {
        List<Payment> payments = getPaymentsForAGroup(groupId);
        return payments;
    }

    public LinkedHashMap<Payment, String> getPaymentDescriptionsForGroupAndPaymentId(Long groupId) {
        LinkedHashMap<Payment, String> paymentList = new LinkedHashMap<>();
        String paymentDescriptions;
        List<Payment> payments = getPaymentsForAGroup(groupId);

        for(Payment payment : payments) {
            User userFrom = userRepository.findById(payment.getPayerId()).get();
            User userTo = payment.getCost().getGroupMember().getUser();
            paymentDescriptions =((int)payment.getSum() + " kr from " + userFrom.getUserName() + " to " + userTo.getUserName());

                payment.setId(payment.getId() + payments.size());
                paymentList.put(payment, paymentDescriptions);

        }
        return paymentList;
    }

    public double calcTotalSumForGroup(List<GroupMember> listOfCostsByGroup) {
        double totalSum = 0;

        for (int i = 0; i < listOfCostsByGroup.size(); i++) {
            for (int j = 0; j < listOfCostsByGroup.get(i).getCosts().size(); j++) {
                totalSum = totalSum + listOfCostsByGroup.get(i).getCosts().get(j).getCost();
            }
        }
        return totalSum;
    }

    public LinkedHashMap<User, Double> calcMembersBalance(double totalSum, List<GroupMember> listOfCostsByGroup) {

        double averageSum = totalSum / listOfCostsByGroup.get(0).getPaybackGroup().getGroupMembers().size();
        LinkedHashMap<User, Double> costMapping = new LinkedHashMap<>();
        LinkedHashMap<User, Double> listOfPaidCosts = new LinkedHashMap<>();


        for (int i = 0; i < listOfCostsByGroup.size(); i++) {
            double owedMoney = 0;
            double usersTotalCost = 0;
            for (int j = 0; j < listOfCostsByGroup.get(i).getCosts().size(); j++) {
                usersTotalCost += listOfCostsByGroup.get(i).getCosts().get(j).getCost();
            }
            owedMoney = usersTotalCost - averageSum;
                costMapping.put(listOfCostsByGroup.get(i).getUser(), owedMoney);
        }

            for (int i = 0; i < listOfCostsByGroup.size(); i++) {
                for (int j = 0; j < listOfCostsByGroup.get(i).getCosts().size(); j++) {
                    for (int k = 0; k < listOfCostsByGroup.get(i).getCosts().get(j).getPayments().size(); k++) {
                        if (listOfCostsByGroup.get(i).getCosts().get(j).getPayments().get(k).isPaybackd()) {
                            User user = userRepository.findById(listOfCostsByGroup.get(i).getCosts().get(j).getPayments().get(k).getPayerId()).get();
                            listOfPaidCosts.put(user, listOfCostsByGroup.get(i).getCosts().get(j).getPayments().get(k).getSum());
                            double sum1 = costMapping.get(listOfCostsByGroup.get(i).getUser());
                            double sum2 = listOfCostsByGroup.get(i).getCosts().get(j).getPayments().get(k).getSum();
                            double totalPayerSum = sum1 - sum2;
                            costMapping.put(listOfCostsByGroup.get(i).getUser(), totalPayerSum);
                        }
                    }
                }
            }

            for (int i = 0; i < costMapping.size(); i++) {
                if (listOfPaidCosts.get(listOfCostsByGroup.get(i).getUser()) != null) {
                    double sum1 = costMapping.get(listOfCostsByGroup.get(i).getUser());
                    double sum2 = listOfPaidCosts.get(listOfCostsByGroup.get(i).getUser());
                    double totalPayerSum = sum1 + sum2;
                    if (totalPayerSum == 0) {
                        costMapping.remove(listOfCostsByGroup.get(i).getUser());
                    } else {
                        costMapping.put(listOfCostsByGroup.get(i).getUser(), totalPayerSum);
                    }
                }
            }
        return costMapping;
    }

    private void createDividedPayment(LinkedHashMap<User, Double> costMapping, List<GroupMember> listOfCostsByGroup) {

        double paymentSum = 0;
        int counter = costMapping.size();
        List<Map.Entry<User, Double>> entryList = findLastEntryWithArrayListMethod(costMapping);

        for (int i = 0; i < counter; i++) {
            for (int j = counter - 1; j >= 0; j--) {
                if (entryList.size() > 1) {
                    paymentSum = entryList.get(j).getValue() + entryList.get(i).getValue();
                    if (paymentSum > 0) {
                        List<Cost> cost = getOneMembersListOfCostsForCreatingPayments(entryList.get(j).getKey(), listOfCostsByGroup, counter);
                        double negCostToPos = Math.abs(entryList.get(i).getValue());
                        paymentRepository.save(new Payment(cost.get(0), false, entryList.get(i).getKey().getId(), negCostToPos));
                        entryList.get(j).setValue(paymentSum);
                        entryList.remove(i); 
                    } else if (paymentSum <= 0) {
                        List<Cost> cost = getOneMembersListOfCostsForCreatingPayments(entryList.get(j).getKey(), listOfCostsByGroup, counter);
                        paymentRepository.save(new Payment(cost.get(0), false, entryList.get(i).getKey().getId(), entryList.get(j).getValue()));
                        entryList.get(i).setValue(paymentSum);
                        entryList.remove(j);
                    }
                }
            }
        }
    }

    private List<Cost> getOneMembersListOfCostsForCreatingPayments(User user, List<GroupMember> listOfCostsByGroup, int counter) {
        List<Cost> cost = new ArrayList<>();
        for (int x = 0; x < counter; x++) {
            if (user.getId() == listOfCostsByGroup.get(x).getUser().getId()) {
                for (int y = 0; y < listOfCostsByGroup.get(x).getCosts().size(); y++) {
                    cost.add(listOfCostsByGroup.get(x).getCosts().get(y));
                }
            }
        }
        return cost;
    }

    private static List<Map.Entry<User, Double>> findLastEntryWithArrayListMethod(LinkedHashMap<User, Double> costMapping) {
        List<Map.Entry<User, Double>> entryList = new ArrayList<Map.Entry<User, Double>>(costMapping.entrySet());
        return entryList;
    }

    private void createEqualPayment(List<Cost> cost, long payerId, double paymentSum) {
        boolean cont = false;

        for (int i = 0; i < cost.size(); i++) {
            if (cost.get(i).getCost() >= paymentSum && !cont) {
                paymentRepository.save(new Payment(cost.get(i), false, payerId, paymentSum));
                cont = true;
            }
        }
        if (!cont) {
            for (int i = 0; i < cost.size(); i++) {
                if (cost.get(i).getCost() < paymentSum) {
                    double test = cost.get(i).getCost();
                    paymentSum = paymentSum - test;
                    paymentRepository.save(new Payment(cost.get(i), false, payerId, test));
                } else if (cost.get(i).getCost() >= paymentSum && paymentSum != 0) {
                    paymentRepository.save(new Payment(cost.get(i), false, payerId, paymentSum));
                    paymentSum = 0;
                }
            }
        }
    }

    private static void orderMapByValue(LinkedHashMap<User, Double> costMap) {
        List<Map.Entry<User, Double>> entries = new ArrayList<>(costMap.entrySet());

        Collections.sort(entries, new Comparator<Map.Entry<User, Double>>() {
            @Override
            public int compare(Map.Entry<User, Double> lhs, Map.Entry<User, Double> rhs) {
                if (((double) lhs.getValue()) > ((double) rhs.getValue())) {
                    return 1;
                } else if ((((double) lhs.getValue()) == ((double) rhs.getValue()))) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });
        costMap.clear();
        for (Map.Entry<User, Double> e : entries) {
            costMap.put(e.getKey(), e.getValue());
        }
    }

    public LinkedHashMap<User, Integer> memberBalanceToInt(LinkedHashMap<User, Double> map) {
        LinkedHashMap<User, Integer> map2 = new LinkedHashMap<>();
        for (User key : map.keySet()) {
            map2.put(key, map.get(key).intValue());
        }
        return map2;
    }
}
