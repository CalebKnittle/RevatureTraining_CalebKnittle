package dev.knittle.daotests;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import dev.knittle.daos.UserDAO;
import dev.knittle.daos.UserDAOImpl;
import dev.knittle.entities.User;

class UserDAOTests {

	UserDAO udao = new UserDAOImpl();
	
	@Test
	void test() {
		fail("Not yet implemented");
	}
	
	void userDAOTest() {
		User testUser = new User();
		testUser = udao.getUserByUsername("Marshall");
		if(testUser != null) {
			System.out.println(testUser);
		}
	}

}
