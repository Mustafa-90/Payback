package com.example.Payback;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaybackApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserController uc;


    @Test
    public void contextLoads() {
    }

    /*@Test
    public void addUser() {
        User user = new User("username", "password", "first name", "last name", "email", "phonenr");
        userRepository.save(user);
        String result = userRepository.findByUserName("username").get().getUserName();

        Assert.assertEquals("username", result);
    }*/

    @Test
    public void deleteUser() {
        User user = new User("Abbe", "password", "first name", "last name", "email", "phonenr");
        String result = uc.addUser(user);
        Assert.assertEquals("Added user", result);

        userRepository.delete(user);
        boolean check = userRepository.findByUserName("Abbe").isPresent();
        Assert.assertEquals(false, check);
    }

    @Test
    public void uniqueUser() {
        User user = new User("Mikaela", "password", "first name", "last name", "email", "phonenr");
        String result = uc.addUser(user);
        Assert.assertEquals("not ok", result);
    }
}
