package com.example.Payback;

import com.example.Payback.Controller.UserController;
import com.example.Payback.Repository.GroupMemberRepository;
import com.example.Payback.Repository.PaybackGroupRepository;
import com.example.Payback.Repository.PaymentRepository;
import com.example.Payback.Repository.UserRepository;
import com.example.Payback.Service.GroupService;
import com.example.Payback.Service.UserService;
import com.example.Payback.Repository.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.List;

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
    UserService userService;

    @Autowired
    GroupService groupService;

    @Autowired
    CostRepository costRepository;

    @Test
    public void contextLoads() {
    }

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
}
