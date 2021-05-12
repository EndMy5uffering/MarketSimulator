package com.test.main;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.market.account.Account;
import com.market.account.User;
import com.market.eco.Commodity;
import com.market.eco.Currency;

class UserTests {

	@Test
	void BasicUserTest01() {
		UUID uuid = UUID.randomUUID();
		User user = new User(uuid.toString(), "TestUser");
		Account account = new Account();
		assertTrue(user != null, "User was null!");
		assertTrue(account != null, "Account was null!");

		assertTrue(user.getUserName() == "TestUser");
		try {
			UUID id = UUID.fromString(user.getUuid());
			assertTrue(id.equals(uuid));
		} catch (Exception e) {
			assertTrue(false, "UUID could not be read as uuid!");
		}
		
		assertTrue(!(user.addToAccount(null)));
		assertTrue(user.addToAccount(account));
		
		assertTrue(account.getUsers().contains(user), "Account did not contain user!");
		
		assertTrue(user.deleteUser(), "Delete function did not unlink user!");
		
		assertTrue(!account.getUsers().contains(user), "Account still contains user after deleat!");
		
	}
	
	@Test
	void BasicUserTest02() {
		UUID uuid = UUID.randomUUID();
		User user = new User(uuid.toString(), "TestUser");
		Account account = new Account();
		assertTrue(user != null, "User was null!");
		assertTrue(account != null, "Account was null!");
		
		assertTrue(account.deposit(5.0));
		
		assertTrue(account.getBalance() == 5.0);
		
		assertTrue(account.withdraw(6.0));
		
		assertTrue(account.getBalance() == -1.0);
		
		assertTrue(account.withdraw(-100.0));
		
		assertTrue(account.getBalance() == -1.0);
		
		assertTrue(account.deposit(-1.0) == false);
		
		assertTrue(user.deleteUser());
		
		assertTrue(account.getUsers().isEmpty());
		
		assertTrue(user.getAccounts().isEmpty());
	}
	
	@Test
	void accountTests03() {

		Account account = new Account();
		
		assertTrue(account.getBalance() == 0);
		
		assertTrue(account.withdraw(2.0));
		
		assertTrue(account.getBalance() == -2.0);

		account.withdraw(Double.MAX_VALUE);
		//assertTrue((double)out == 0.0, "Expected: 0.0 but it was " + out);
		
		//assertTrue(account.deposit(Double.MAX_VALUE) == true);
		
		//assertTrue(account.getBalance() == Double.MAX_VALUE - 1.0);
		
		//assertTrue(account.deposit(5) == false);
		
		//assertTrue(account.getBalance() == Double.MAX_VALUE - 1.0);
	}

}
