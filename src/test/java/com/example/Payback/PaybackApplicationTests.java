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
	UserController userController;

	@Test
	public void contextLoads() {
	}

//	@Test
//	public void addUser() {
//		User user = new User("username", "password", "first name", "last name", "email", "phone number");
//		userRepository.save(user);
//		String result = userRepository.findByUserName("username").get().getUserName();
//		Assert.assertEquals("username", result);
//	}

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

}
