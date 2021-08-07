package betterTestBankAccount;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {
	private Account a;
	private Account a16000;

	@Before
	public void setUp() throws Exception {
		System.out.println("setUp()");
		a = new Account(1, 3000d);
		a16000 = new Account(2, 16000d);
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("tearDown()");
	}

	@Test
	public void withdrawing3000ShouldResultInBalance0() {
		System.out.println("testRedraw()");
		boolean result = a.withdraw(3000);
		assertTrue(result);
		double expBal = 0d;
		assertEquals(expBal, a.getBalance(), 0d);
	}

	@Test
	public void withdrawing3001ShouldResultInBalance3000() {
		System.out.println("withdrawing3001ShouldResultInBalance3000()");
		boolean result = a.withdraw(3001);
		assertFalse(result);
		double expBal = 3000d;
		assertEquals(expBal, a.getBalance(), 0d);
	}

	@Test
	public void withdrawing10ShouldResultInBalance2990() {
		System.out.println("withdrawing10ShouldResultInBalance2990()");
		boolean result = a.withdraw(10);
		assertTrue(result);
		double expBal = 2990d;
		assertEquals(expBal, a.getBalance(), 0d);
	}

	@Test
	public void withdrawing9ShouldResultInBalance3000() {
		System.out.println("withdrawing9ShouldResultInBalance3000()");
		boolean result = a.withdraw(9);
		assertFalse(result);
		double expBal = 3000d;
		assertEquals(expBal, a.getBalance(), 0d);
	}

	@Test
	public void withdrawing15000ShouldResultInBalance1000() {
		System.out.println("withdrawing15000ShouldResultInBalance1000()");
		boolean result = a16000.withdraw(15000);
		assertTrue(result);
		double expBal = 1000d;
		assertEquals(expBal, a16000.getBalance(), 0d);
	}

	@Test
	public void withdrawing15001ShouldResultInBalance16000() {
		System.out.println("withdrawing15001ShouldResultInBalance16000()");
		boolean result = a16000.withdraw(15001);
		assertFalse(result);
		double expBal = 16000d;
		assertEquals(expBal, a16000.getBalance(), 0d);
	}

}
