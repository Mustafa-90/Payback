package com.example.Payback;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaybackApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserController userController;

    @Autowired
    GroupRepository groupRepository;

    @Autowired
    PaymentRepository paymentRepository;


    @Test
    public void contextLoads() {
    }

    @Test
    public void uniqueUser() {
        User user = new User("Tommy", "password", "Test2", "last name", "email", "phone number");
        String result = userController.addUser(user);
        Assert.assertEquals("Added user", result);

        User user2 = new User("Mikaela", "password", "Test2", "last name", "email", "phone number");
        String result2 = userController.addUser(user2);
        Assert.assertEquals("Failed to add user", result2);
    }

    @Test
    public void deleteUser() {
        userRepository.delete(userRepository.findByUserName("Tommy").get());
        Boolean userResult = userRepository.findByUserName("Tommy").isPresent();
        Assert.assertEquals(false, userResult);
    }

    @Test
    public void getGroups() {
        List<PaybackGroup> paybackGroups = (List) groupRepository.findAll();
        Assert.assertEquals(true, paybackGroups.size() > 0);
    }


    @Test
    public void getAllPayments() {
        List<Payment> result = (List) paymentRepository.findAll();
        Assert.assertEquals(true, result.size() > 0);
    }

    @Test
    public void getAllUsers() {
        List<User> result = (List) userRepository.findAll();
        Assert.assertEquals(true, result.size() > 0);
    }
}
