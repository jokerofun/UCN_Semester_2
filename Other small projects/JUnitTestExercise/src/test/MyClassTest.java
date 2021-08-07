package test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class MyClassTest {

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionIsThrown() {
		MyClass tester = new MyClass();
		tester.multiply(1000, 5);
	}

	@Test
	public void testMultiply() {
		MyClass tester = new MyClass();
		assertEquals("10 x 5 must be 50", 50, tester.multiply(10, 5));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testException() {
		MyClass tester = new MyClass();
		Assertions.assertThrows(IllegalArgumentException.class, () -> tester.multiply(1000, 2000),
				"Expected multiply() to throw, but it didn't");
	}
}