package com.example.Payback;

import com.example.Payback.Controller.UserController;
import com.example.Payback.Repository.*;
import com.example.Payback.Service.GroupService;
import com.example.Payback.Service.UserService;
import com.example.Payback.Repository.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.parameters.P;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
<<<<<<< HEAD
=======
import javax.validation.constraints.AssertTrue;
import java.util.HashMap;
import java.util.LinkedHashMap;
>>>>>>> 64edc1300c8f3a82f02e58c7ce8a3f714e30dca8
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class PaybackApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserController userController;

    @Autowired
    PaymentRepository paymentRepository;

    @Autowired
    PaybackGroupRepository paybackGroupRepository;

    @Autowired
    GroupMemberRepository groupMemberRepository;

    @Autowired
<<<<<<< HEAD
    UserService userService;
=======
	CostRepository costRepository;

    @Autowired
	UserService userService;
>>>>>>> 64edc1300c8f3a82f02e58c7ce8a3f714e30dca8

    @Autowired
    GroupService groupService;

    @Autowired
    CostRepository costRepository;

    @Test
    public void contextLoads() {
    }

<<<<<<< HEAD
/*    @Test
    public void addUser() {
        User user = new User("username", "password", "first name", "last name", "email", "phone number");
        userRepository.save(user);
        String result = userRepository.findByUserName("username").get().getUserName();
        Assert.assertEquals("username", result);
    }*/

    //
//	@Test
//	public void uniqueUser() {
//		User user = new User("Tommy", "password", "Test2", "last name", "email", "phone number");
//		String result = userController.addUser(user);
//		Assert.assertEquals("Added user", result);
//
//		User user2 = new User("Mikaela", "password", "Test2", "last name", "email", "phone number");
//		String result2 = userController.addUser(user2);
//		Assert.assertEquals("username", result2);
//	}
//
    @Test
    public void addUser() {
        User user = new User("username", "password", "first name", "last name", "email", "phone number");
        userRepository.save(user);
        String result = userRepository.findByUserName("username").get().getUserName();
        Assert.assertEquals("username", result);
    }

    @Test
    public void uniqueUser() {
        User user = new User("Tommy", "password", "Test2", "last name", "email", "phone number");
        String result = userService.addUser(user);
        Assert.assertEquals("Added user", result);

        User user2 = new User("Mikaela", "password", "Test2", "last name", "email", "phone number");
        String result2 = userService.addUser(user2);
        Assert.assertEquals("username", result2);
    }
=======
	@Test
	public void addUser() {
		User user = new User("username", "password", "first name", "last name", "email", "phone number");
		userRepository.save(user);
		String result = userRepository.findByUserName("username").get().getUserName();
		Assert.assertEquals("username", result);
	}

	@Test
	public void uniqueUser() {
		User user = new User("Tommy12", "password", "Test2", "last name", "email12", "23124324");
		String result = userService.addUser(user);
		Assert.assertEquals("Added user", result);

		User user2 = new User("Mikaela", "password", "Test2", "last name", "email", "phone number");
		String result2 = userService.addUser(user2);
		Assert.assertEquals("username", result2);
	}
>>>>>>> 64edc1300c8f3a82f02e58c7ce8a3f714e30dca8

//	@Test
//	public void deleteUser() {
//		userRepository.delete(userRepository.findByUserName("Tommy").get());
//		Boolean userResult = userRepository.findByUserName("Tommy").isPresent();
//		Assert.assertEquals(false, userResult);
//	}
//
//	@Test
//	public void updateUser() {
//		User user = userRepository.findByUserName("Mikaela").get();
//		user.setLastName("S");
//		userController.updateUser(user);
//
//		User userResult = userRepository.findByUserName("Mikaela").get();
//
//		Assert.assertEquals("S", userResult.getLastName());
//	}
//
//	@Test
//	public void uniqueUser() {
//		User user = new User("Tommy", "password", "Test2", "last name", "email", "phone number");
//		String result = userController.addUser(user);
//		Assert.assertEquals("Added user", result);
//
//		User user2 = new User("Mikaela", "password", "Test2", "last name", "email", "phone number");
//		String result2 = userController.addUser(user2);
//		Assert.assertEquals("Failed to add user", result2);
//	}
//
//	@Test
//	public void deleteUser() {
//		userRepository.delete(userRepository.findByUserName("Tommy").get());
//		Boolean userResult = userRepository.findByUserName("Tommy").isPresent();
//		Assert.assertEquals(false, userResult);
//	}
//
//	@Test
//	public void getAllPayments() {
//		List<Payment> result = (List) paymentRepository.findAll();
//		Assert.assertEquals(true, result.size() > 0);
//	}

    @Test

    public void getAllGroupMembers() {
        List<GroupMember> members = groupMemberRepository.findByPaybackGroupId(2L);
        for (int i = 0; i < members.size(); i++) {
            System.out.println(members.get(i).getUser().getId() + ", " + members.get(i).getUser().getFirstName());
        }
    }

    @Test
    public void getAllGroupsForOneMember() {
        List<GroupMember> listOfGroups = groupMemberRepository.findByUserId(6L);

        for (int i = 0; i < listOfGroups.size(); i++) {
            System.out.println(listOfGroups.get(i).getUser().getFirstName() + ", " + listOfGroups.get(i).getPaybackGroup().getGroupName());
        }
    }

    @Test
    public void findByUsername() {
        User user = userRepository.findByUserName("Mustafa").get();
    }

    @Test
    public void getTotalSumByGroupId() {
        PaybackGroup pbg = paybackGroupRepository.findById(2L).get();
        System.out.println(pbg.getTotalSum());
    }

    @Test
    public void testX() {
        List<GroupMember> members = groupMemberRepository.findByPaybackGroupId(2L);
        Double totalSum = paybackGroupRepository.findById(2L).get().getTotalSum();
        Double calculatedCost = totalSum / members.size();

        PaybackGroup pbg = members.get(0).getPaybackGroup();
        paybackGroupRepository.save(pbg);
    }

    @Test
    public void addCostsForGroup() {
        List<GroupMember> listOfCosts = groupMemberRepository.findByPaybackGroupId(2L);
        System.out.println(listOfCosts.get(1).getPaybackGroup().getGroupName());
        System.out.println(listOfCosts.get(1).getUser().getFirstName());
        System.out.println(listOfCosts.get(1).getCosts().get(0).getCost());
        System.out.println(listOfCosts.get(1).getCosts().get(0).getPayments().get(0));
    }

    @Test
    public void addGroupMember() {
//    	User user = new User("J", "pw", "J", "S", "J@S.se", "112");
//    	userService.addUser(user);
        User user = userRepository.findByUserName("Tommy").get();
        PaybackGroup group = new PaybackGroup("Namn");
        paybackGroupRepository.save(group);
        //groupService.addGroupMember(user, group);
    }

<<<<<<< HEAD
    @Test
    public void sumsGroupsCosts() {
        List<GroupMember> listOfCosts = groupMemberRepository.findByPaybackGroupId(2L);

        System.out.println(listOfCosts.get(0).getCosts().size());
    }

    @Test
    public void createCost() {
        GroupMember groupMember = groupMemberRepository.findById(2L).get();
        Cost cost = new Cost();
        costRepository.save(cost);
    }
=======
//	@Test
//	public void addCostsForGroup() {
//		List<GroupMember> listOfCost = groupMemberRepository.findByPaybackGroupId(2L);
//		double sum = 0;
//		for (int i = 0; i < listOfCost.size(); i++) {
//			for (int j = 0; j < listOfCost.get(i).getCosts().size(); j++) {
//				sum = sum + listOfCost.get(i).getCosts().get(j).getCost();
//			}
//		}
//		listOfCost.get(0).getPaybackGroup().setTotalSum(sum);
//		paybackGroupRepository.save(listOfCost.get(0).getPaybackGroup());
//	}

	@Test
	public void addMembersPayments() {
    	List<Payment> membersPayment = paymentRepository.findByPayerId(4L);
    	Assert.assertEquals(100, membersPayment.get(1).getSum());
	}

	@Test
	public void getMembersPayments() {
    	List<GroupMember> groupMemberPaymentList = groupMemberRepository.findByUserId(1L);

		for (int i = 0; i < groupMemberPaymentList.size(); i++) {
			System.out.println(groupMemberPaymentList.get(i).getUser().getFirstName());
			for (int j = 0; j < groupMemberPaymentList.get(i).getCosts().get(0).getPayments().size(); j++) {
				System.out.println(groupMemberPaymentList.get(i).getCosts().get(0).getPayments().get(j).getSum());
			}
		}
		Assert.assertEquals(80, groupMemberPaymentList.get(0).getCosts().get(0).getPayments().get(1).getSum());
	}

	@Test
	public void createCost() {
    	GroupMember groupMember = groupMemberRepository.findById(2L).get();
    	Cost cost = new Cost(groupMember, 1000.0, "Mat", null);
    	costRepository.save(cost);
	}

	@Test
	public void splitCost() {
    	Cost cost = costRepository.findById(11L).get();
    	for (int i = 0; i < cost.getGroupMember().getPaybackGroup().getGroupMembers().size(); i++) {
			System.out.println(cost.getGroupMember().getPaybackGroup().getGroupMembers().get(i).getUser().getFirstName());
		}
		System.out.println(cost.getCost());
    	double splitCost = cost.getCost() / cost.getGroupMember().getPaybackGroup().getGroupMembers().size();
		System.out.println(splitCost);

		for (int i = 0; i < cost.getGroupMember().getPaybackGroup().getGroupMembers().size(); i++) {
			if (cost.getGroupMember().getUser().getId() != cost.getGroupMember().getPaybackGroup().getGroupMembers().get(i).getUser().getId()) {
				paymentRepository.save(new Payment(cost, false, cost.getGroupMember().getPaybackGroup().getGroupMembers().get(i).getUser().getId(), splitCost));
			}
		}
	}

	@Test
	public void splitEqualCosts() {
    	List<GroupMember> listOfCostsByGroup = groupMemberRepository.findByPaybackGroupId(3);

		for (int i = 0; i < listOfCostsByGroup.size(); i++) {
			System.out.println(listOfCostsByGroup.get(i).getUser().getFirstName() + " is owed " + listOfCostsByGroup.get(i).getCosts().get(0).getPayments().get(0).getSum() + " by: " ); {
				for (int j = 0; j < listOfCostsByGroup.get(i).getCosts().get(0).getPayments().size(); j++) {
					User user = userRepository.findById(listOfCostsByGroup.get(i).getCosts().get(0).getPayments().get(j).getPayerId()).get();
					System.out.println(user.getFirstName());
				}
			}
		}

	}

	@Test
	public void testingSplitCost() {
		List<GroupMember> listOfCostsByGroup = groupMemberRepository.findByPaybackGroupId(3L);
		double totalSum = 0;

		for (int i = 0; i < listOfCostsByGroup.size(); i++) {
			for (int j = 0; j < listOfCostsByGroup.get(i).getCosts().size(); j++) {
				totalSum = totalSum + listOfCostsByGroup.get(i).getCosts().get(j).getCost();
			}
		}
		double averageSum = totalSum / listOfCostsByGroup.get(0).getPaybackGroup().getGroupMembers().size();
		LinkedHashMap<User, Double> costMapping = new LinkedHashMap<>();


		for (int i = 0; i < listOfCostsByGroup.size(); i++) {
			double owedMoney = 0;
			double usersTotalCost = 0;
			for (int j = 0; j < listOfCostsByGroup.get(i).getCosts().size(); j++) {
				usersTotalCost += listOfCostsByGroup.get(i).getCosts().get(j).getCost();
			}
			owedMoney = usersTotalCost - averageSum;
			costMapping.put(listOfCostsByGroup.get(i).getUser(), owedMoney);
		}

		for (int i = 0; i < costMapping.size(); i++) {
			for (int j = i+1; j < costMapping.size(); j++) {
				if (costMapping.get(listOfCostsByGroup.get(i).getUser()) + costMapping.get(listOfCostsByGroup.get(j).getUser()) == 0) {
					System.out.println(costMapping.get(listOfCostsByGroup.get(i).getUser()));
					if (costMapping.get(listOfCostsByGroup.get(i).getUser()) < 0) {
						createPayment(listOfCostsByGroup.get(i).getCosts(), listOfCostsByGroup.get(i).getUser().getId(), costMapping.get(listOfCostsByGroup.get(j).getUser()));
					}
						costMapping.get(listOfCostsByGroup.get(i).getUser());
				}
			}
		}
	}

	public void createPayment (List<Cost> cost, long payerId, double paymentSum) {

    	Payment payment;
    	boolean cont = false;

    	for (int i = 0; i < cost.size(); i++) {
			if (cost.get(i).getCost() >= paymentSum && !cont) {
				payment = new Payment(cost.get(i), false, payerId, paymentSum);
				cont = true;
			}
		}
    	if (!cont) {
    		for (int i = 0; i < cost.size(); i++) {
    			if (cost.get(i).getCost() < paymentSum) {
					double test = cost.get(i).getCost();
					paymentSum = paymentSum - test;
					payment = new Payment(cost.get(i), false, payerId, test);
				} else if (cost.get(i).getCost() >= paymentSum && paymentSum != 0) {
					payment = new Payment(cost.get(i), false, payerId, paymentSum);
					paymentSum = 0;
				}
			}
		}
	}
>>>>>>> 64edc1300c8f3a82f02e58c7ce8a3f714e30dca8
}

//		1200 / 4 = 300
//		A: 300 - 150 = -150
//		B: 300 - 450 = 150
//		C: 300 - 600 = 300
//		D: 300 - 0 = -300
